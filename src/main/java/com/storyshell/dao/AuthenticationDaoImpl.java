package com.storyshell.dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.storyshell.model.UserDetail;
import com.storyshell.util.CustomRowMapper;

/**
 * @author santoshkumar
 *
 */
@Repository("userDetail")
public class AuthenticationDaoImpl implements AuthenticationDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public UserDetail getUserDetail(String email) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from userdetail where email='"+email+"'";
		List<UserDetail> userDetail = jdbcTemplate.query(sql, new CustomRowMapper());
		return userDetail.get(0);
	}

	public boolean isUserExists(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean emailExists(String email) {
		String sql = "select * from userdetail where email='"+email+"'";
	    List<UserDetail> user = jdbcTemplate.query(sql, new CustomRowMapper());
	    if(user.size() > 0){
	    	return true;
	    }
		return false;
	}

	public boolean mobileNoExits(String mobileNo) {

		List<UserDetail> user = null;
		try {
			String sql = "select * from userdetail where mobilenumber='"+ mobileNo + "'";
			user = jdbcTemplate.query(sql, new CustomRowMapper());
		} catch (Exception e) {
			// TODO: handle exception
		}
	    if(user.size() > 0){
	    	return true;
	    }
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

	public int addAccount(UserDetail user) throws Exception {
	    try {
			String sql = "insert into userdetail (`firstname`, `lastname`, `email`, `mobileNumber`, `createdDate`, `modifiedDate`, `password`) values(?,?,?,?,?,?,?)";
			 return jdbcTemplate.update(sql,new Object[] {user.getFirstName(), user.getLastName(),
							user.getEmail(), user.getMobileNumber(),new java.util.Date().toString(),new java.util.Date().toString(), user.getPassword()});
		} catch (Exception e) {
			// TODO: handle exception
			throw  e;
		}
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
