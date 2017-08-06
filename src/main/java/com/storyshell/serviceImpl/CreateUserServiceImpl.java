package com.storyshell.serviceImpl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.model.UserDetail;
import com.storyshell.services.ICreateUserService;
import com.storyshell.util.EncryptionDecryption;
import com.storyshell.util.PropertiesUtil;
import com.storyshell.util.ResponseGenerator;
import com.storyshell.util.Constants.Common;

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
	
	@Override
	public Response processCreateUser(UserDetail userDetail) {
		if(authenticationDao.mobileNoExits(userDetail.getMobileNumber())){
			return ResponseGenerator.generateResponse("User already registered with us with the same mobile numer");
		}
		else if(authenticationDao.emailExists(userDetail.getEmail())){
			return ResponseGenerator.generateResponse("User already registered with us with same email id");
		}
		else{
			userDetail.setPassword(getEncryptedPassword(userDetail.getPassword()));
			if (authenticationDao.addAccount(userDetail) == 1) {
				return ResponseGenerator.generateResponse("User created successfully");
			}
			return ResponseGenerator.generateResponse("error while creating User");
		}
	}
	
	private String getEncryptedPassword(String password){
		String saltedpassword = propertiesUtil.getProperty(Common.KEY, Common.DEFAULT_KEY) + password;
		String hashedPassword = EncryptionDecryption.generateHash(saltedpassword);
		return hashedPassword;
	}

}
