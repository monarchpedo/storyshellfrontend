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
import com.storyshell.util.GenericExceptionHandler;
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

	/**
	 * return response on login process
	 * */
	@Override
	public Response processLogin(LoginCredential loginDetails) throws Exception {
		try {
			String encPassword = getEncryptedPassword(loginDetails.getPassword());
			UserDetail userDetail = authenticationService.getUserDetail(loginDetails.getEmail());
			if (null != userDetail) {
				if(encPassword.equals(userDetail.getPassword())){
					return ResponseGenerator.generateResponse("Password matched...Enjoy",Response.Status.ACCEPTED);
				}
				return ResponseGenerator.generateResponse("Password didn't match.. please try again",Response.Status.NOT_ACCEPTABLE);
			}
			return ResponseGenerator.generateResponse("Email not registered with us.. Please register first",Response.Status.NOT_FOUND);
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	/**
	 * return encrypted password
	 * */
	private String getEncryptedPassword(String password) {
		String saltedpassword = propertiesUtil.getProperty(Common.KEY,Common.DEFAULT_KEY) + password;
		String hashedPassword = EncryptionDecryption.generateHash(saltedpassword);
		return hashedPassword;
	}

	/**
	 * return response for forget password
	 * */
	@Override
	public Response processForgetPassword(String email) throws MessagingException {
		String response = mailConfiguration.sendMail(email,passwordResetString(email));
		return ResponseGenerator.generateResponse(response,Response.Status.ACCEPTED);
	}
	
	/**
	 * return reset password link to user email with unique id
	 * */
	private String passwordResetString(String email) {
		StringBuilder builder = new StringBuilder();
		builder.append("http://localhost:8080/oauth/v1/user/forgotpassword/");
        String randomUUIDString = UUID.randomUUID().toString();
        builder.append(randomUUIDString);
        redisUtility.saveObject(UserDetails.REDIS_KEY_FORGET_PASS, randomUUIDString, email);
        return builder.toString();
	}

	/**
	 * it just check the reset password link and send response to frontend to send new password set page
	 * */
	@Override
	public Response processVerifyResetpassword(String key) {
		String email = (String) redisUtility.findKey(UserDetails.REDIS_KEY_FORGET_PASS, key);
		if(null != email) {
			return ResponseGenerator.generateResponse("reset password verified",Response.Status.ACCEPTED);
		}
		return null;
	}

	/**
	 * it return the userId of user exixts or not
	 */
	@Override
	public Response checkUser(int userId) {
		boolean count = authenticationService.isUserExists(userId);
		return ResponseGenerator.generateResponse(count,Response.Status.OK);
	}
	
	

}
