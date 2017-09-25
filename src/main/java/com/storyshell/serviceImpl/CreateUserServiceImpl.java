package com.storyshell.serviceImpl;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.dao.RedisRepository;
import com.storyshell.mailsender.MailConfiguration;
import com.storyshell.model.UserDetail;
import com.storyshell.services.ICreateUserService;
import com.storyshell.util.EncryptionDecryption;
import com.storyshell.util.PropertiesUtil;
import com.storyshell.util.ResponseGenerator;
import com.storyshell.util.Constants.Common;
import com.storyshell.util.Constants.UserDetails;

/**
 * @author santoshkumar
 *
 */
@Service
public class CreateUserServiceImpl implements ICreateUserService {

	@Autowired
	public AuthenticationDao authenticationDao;
	
	@Autowired
	public PropertiesUtil propertiesUtil;
	
	@Autowired
	public MailConfiguration mailConfiguration;
	
	@Autowired
	public RedisRepository redisUtility;
	
	@Override
	public Response processCreateUser(UserDetail userDetail) throws MessagingException {
		if(authenticationDao.mobileNoExits(userDetail.getMobileNumber())){
			return ResponseGenerator.generateResponse("User already registered with us with the same mobile numer");
		}
		else if(authenticationDao.emailExists(userDetail.getEmail())){
			return ResponseGenerator.generateResponse("User already registered with us with same email id");
		}
		else{
			userDetail.setPassword(getEncryptedPassword(userDetail.getPassword()));
			if (redisUtility.saveObject(UserDetails.REDIS_KEY_CREATE, userDetail.getEmail(), userDetail)) {
				String msg = accountActivationString(userDetail);
				mailConfiguration.sendMail(userDetail.getEmail(), msg);
				return ResponseGenerator.generateResponse("Email has been sent. Verify your account"); 
			}
			return ResponseGenerator.generateResponse("error while creating User");
		}
	}
	
	private String getEncryptedPassword(String password){
		String saltedpassword = propertiesUtil.getProperty(Common.KEY, Common.DEFAULT_KEY) + password;
		String hashedPassword = EncryptionDecryption.generateHash(saltedpassword);
		return hashedPassword;
	}
	
	private String accountActivationString(UserDetail userDetail) {
		StringBuilder builder = new StringBuilder();
		builder.append("http://localhost:8080/oauth/v1/create/account-activation/");
        String randomUUIDString = UUID.randomUUID().toString();
        builder.append(randomUUIDString);
        redisUtility.saveObject(UserDetails.REDIS_KEY_CREATE, randomUUIDString, userDetail.getEmail());
        return builder.toString();
	}

	@Override
	public Response processVerifyUser(String key) throws Exception {
		String email = (String) redisUtility.findKey(UserDetails.REDIS_KEY_CREATE, key);
		if(null != email) {
			UserDetail userDetail = (UserDetail) redisUtility.findKey(UserDetails.REDIS_KEY_CREATE, email);
			if(null != userDetail) {
				if(authenticationDao.addAccount(userDetail) == 1) {
					return ResponseGenerator.generateResponse("successfully validated");
				}
			}
		}
		return ResponseGenerator.generateResponse("user verification failed");
	}

}
