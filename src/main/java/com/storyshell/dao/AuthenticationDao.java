package com.storyshell.dao;

import java.sql.SQLException;
import java.util.List;

import com.storyshell.model.Location;
import com.storyshell.model.ProfileModel;
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
	
	public int addProfile(int userId,ProfileModel profile);
	
	public int deleteProfile(int userId);

	public int updateProfileImage(int userId,String profileImageLoc);
	
	public int updateProfile(int userId,ProfileModel profile);
	
	public int addLocation(int userId,Location loc);
	
	public int updateLocation(int userId,Location loc);
	
	public ProfileModel getprofile(int userId);
	
	public List<ProfileModel> getProfileList(String interestSection);
	
}