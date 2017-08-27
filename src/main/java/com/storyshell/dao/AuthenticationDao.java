package com.storyshell.dao;

import java.sql.SQLException;

import com.storyshell.model.UserDetail;

public interface AuthenticationDao {

	public UserDetail getUserDetail(String email) throws SQLException;

	public boolean isUserExists(int userId);

	public boolean emailExists(String email);

	public boolean mobileNoExits(String mobileNo);

	public boolean passwordCheck(int userId, String password);

	public boolean passwordCheck(String userData, String password, int checkType);

	public int updateMobileNumber(int userId, String mobilenNo);

	public int updateMobileNumber(String email, String mobileNo);

	public int updatePassword(String userData, String newPassword, int updateType);

	public int resetPassword(String userData, String oldPassword, String newPassword, int resetType);

	public int addAccount(UserDetail userDetail);

	public int deleteAccount(int userId);

	public int deleteAccount(String userData, int flagType);
}
