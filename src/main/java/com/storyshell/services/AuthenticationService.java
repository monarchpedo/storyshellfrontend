package com.storyshell.services;

import java.sql.SQLException;

import com.storyshell.model.UserDetail;

public interface AuthenticationService {
	public int addUserAccount(UserDetail userDetail);

	public int deleteUserAccount(String email, int flagType);

	public int checkUserAccount(String userData, int flagType);

	public boolean checkPassword(String userData, String password, int flagType);

	public boolean checkEmail(String email);

	public boolean checkMobileNumber(String mobileNo);

	public int updateUserAccount(UserDetail userDetail);

	public UserDetail getUserAccount() throws SQLException;

}
