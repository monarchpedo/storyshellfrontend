package com.storyshell.serviceImpl;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.model.UserDetail;
import com.storyshell.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Inject
	private AuthenticationDao authDao;

	public int addUserAccount(UserDetail userDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteUserAccount(String email, int flagType) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int checkUserAccount(String userData, int flagType) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean checkPassword(String userData, String password, int flagType) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkMobileNumber(String mobileNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public int updateUserAccount(UserDetail userDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	public UserDetail getUserAccount() throws SQLException {
		// TODO Auto-generated method stub
		UserDetail userDetail = authDao.getUserDetail();
		return userDetail;
	}

}
