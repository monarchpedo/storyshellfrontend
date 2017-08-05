package com.storyshell.serviceImpl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.model.UserDetail;
import com.storyshell.services.ICreateUserService;
import com.storyshell.util.ResponseGenerator;

/**
 * @author santoshkumar
 *
 */
@Service
public class CreateUserServiceImpl implements ICreateUserService {

	@Autowired
	public AuthenticationDao authenticationDao;
	
	@Override
	public Response processCreateUser(UserDetail userDetail) {
		if(authenticationDao.mobileNoExits(userDetail.getMobileNumber())){
			return ResponseGenerator.generateResponse("User already registered with us with the same mobile numer");
		}
		else if(authenticationDao.emailExists(userDetail.getEmail())){
			return ResponseGenerator.generateResponse("User already registered with us with same email id");
		}
		else{
			if (authenticationDao.addAccount(userDetail) == 1) {
				return ResponseGenerator.generateResponse("User created successfully");
			}
			return ResponseGenerator.generateResponse("error while creating User");
		}
	}

}
