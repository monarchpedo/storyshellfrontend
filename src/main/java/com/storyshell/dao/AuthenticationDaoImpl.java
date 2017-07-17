package com.storyshell.dao;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.storyshell.model.UserDetail;
import com.storyshell.util.CustomRowMapper;

@Repository("userDetail")
public class AuthenticationDaoImpl implements AuthenticationDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public UserDetail getUserDetail() throws SQLException {
		// TODO Auto-generated method stub
		UserDetail userDetail = null;
		userDetail = (UserDetail) jdbcTemplate.query("select * from userdetail", new CustomRowMapper());
		return userDetail;
	}

	public boolean isUserExists(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mobileNoExits(String mobileNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean passwordCheck(int userId, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean passwordCheck(String userData, String password, int checkType) {
		// TODO Auto-generated method stub
		return false;
	}

	public int updateMobileNumber(int userId, String mobilenNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateMobileNumber(String email, String mobileNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updatePassword(String userData, String newPassword, int updateType) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int resetPassword(String userData, String oldPassword, String newPassword, int resetType) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int addAccount(UserDetail userDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteAccount(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteAccount(String userData, int flagType) {
		// TODO Auto-generated method stub
		return 0;
	}

}
