package com.storyshell.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.Profile;

import com.storyshell.model.Location;
import com.storyshell.model.UserDetail;

public interface AuthenticationDao {

	public UserDetail getUserDetail(String email) throws SQLException;

	public boolean isUserExists(int userId);

	public boolean emailExists(String email) throws Exception;

	public boolean mobileNoExits(String mobileNo) throws Exception;

	public boolean passwordCheck(int userId, String password);

	public boolean passwordCheck(String userData, String password, int checkType);

	public int updateMobileNumber(int userId, String mobilenNo);

	public int updateMobileNumber(String email, String mobileNo);

	public int updatePassword(int userId, String newPassword);

	public int resetPassword(String userData, String oldPassword, String newPassword, int resetType);

	public int addAccount(UserDetail userDetail) throws Exception;

	public int deleteAccount(int userId);

	public int deleteAccount(String userData, int flagType);
	
	public int addProfile(Profile profile);
	
	public int deleteProfile(Profile profile);
	
	public int updateProfile(Profile profile);
	
	public int addLocation(Location loc);
	
	public int updateLocation(Location loc);
	
	public Profile getprofile(int userId);
	
	public List<Profile> getProfileList(String interestSection);
	
}