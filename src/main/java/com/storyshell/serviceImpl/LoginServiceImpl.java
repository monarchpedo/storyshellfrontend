package com.storyshell.serviceImpl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.model.LoginCredential;
import com.storyshell.model.UserDetail;
import com.storyshell.services.ILoginService;
import com.storyshell.util.Constants.Common;
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

}
