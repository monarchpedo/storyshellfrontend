package com.storyshell.serviceImpl;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.dao.RedisRepository;
import com.storyshell.mailsender.MailConfiguration;
import com.storyshell.model.LoginCredential;
import com.storyshell.model.UserDetail;
import com.storyshell.services.ILoginService;
import com.storyshell.util.Constants.Common;
import com.storyshell.util.Constants.UserDetails;
import com.storyshell.util.EncryptionDecryption;
import com.storyshell.util.PropertiesUtil;
import com.storyshell.util.ResponseGenerator;

/**
 * @author santoshkumar
 *
 */
@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	public PropertiesUtil propertiesUtil;
	@Autowired
	public AuthenticationDao authenticationService;
	@Autowired
	public MailConfiguration mailConfiguration;
	@Autowired
	public RedisRepository redisUtility;

	@Override
	public Response processLogin(LoginCredential loginDetails) {
		try {
			String encPassword = getEncryptedPassword(loginDetails.getPassword());
			UserDetail userDetail = authenticationService.getUserDetail(loginDetails.getEmail());
			if (null != userDetail) {
				if(encPassword.equals(userDetail.getPassword())){
					return ResponseGenerator.generateResponse("Password matched...Enjoy");
				}
				return ResponseGenerator.generateResponse("Password didn't match.. please try again");
			}
			return ResponseGenerator.generateResponse("Email not registered with us.. Please register first");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseGenerator.generateResponse("System Error..sorry for inconvinence");
	}

	private String getEncryptedPassword(String password) {
		String saltedpassword = propertiesUtil.getProperty(Common.KEY,Common.DEFAULT_KEY) + password;
		String hashedPassword = EncryptionDecryption.generateHash(saltedpassword);
		return hashedPassword;
	}

	@Override
	public Response processForgetPassword(String email) throws MessagingException {
		String response = mailConfiguration.sendMail(email,passwordResetString(email));
		return ResponseGenerator.generateResponse(response);
	}
	
	private String passwordResetString(String email) {
		StringBuilder builder = new StringBuilder();
		builder.append("http://localhost:8080/oauth/v1/user/forgotpassword/");
        String randomUUIDString = UUID.randomUUID().toString();
        builder.append(randomUUIDString);
        redisUtility.saveObject(UserDetails.REDIS_KEY_FORGET_PASS, randomUUIDString, email);
        return builder.toString();
	}

	@Override
	public Response processVerifyResetpassword(String key) {
		String email = (String) redisUtility.findKey(UserDetails.REDIS_KEY_FORGET_PASS, key);
		if(null != email) {
			return ResponseGenerator.generateResponse("reset password verified");
		}
		return null;
	}

}
