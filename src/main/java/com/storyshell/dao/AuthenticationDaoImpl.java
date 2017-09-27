package com.storyshell.dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.storyshell.model.UserDetail;
import com.storyshell.util.CustomRowMapper;
import com.storyshell.util.GenericExceptionHandler;

/**
 * @author santoshkumar
 *
 */
@Repository("userDetail")
public class AuthenticationDaoImpl implements AuthenticationDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public UserDetail getUserDetail(String email) throws SQLException {
		String sql = "select * from userdetail where email='" + email + "'";
		try {
			List<UserDetail> userDetail = jdbcTemplate.query(sql, new CustomRowMapper());
			return userDetail.get(0);
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	public boolean isUserExists(int userId) {
		String sql = "select count(*) from userdetail where userId=?";
		try {
			int userCount = jdbcTemplate.queryForObject(sql, new Object[] { userId }, Integer.class);
			if (userCount > 0 || userCount == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	public boolean emailExists(String email) throws Exception {
		String sql = "select * from userdetail where email='" + email + "'";
		try {
			List<UserDetail> user = jdbcTemplate.query(sql, new CustomRowMapper());
			if (user.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}

	}

	public boolean mobileNoExits(String mobileNo) throws Exception {

		List<UserDetail> user = null;
		try {
			String sql = "select * from userdetail where mobilenumber='" + mobileNo + "'";
			user = jdbcTemplate.query(sql, new CustomRowMapper());
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		if (user.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean passwordCheck(int userId, String password) {
		String sql = "select count(*) from userdetail where userId = ? and password = ?";
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(sql, new Object[] { userId, password }, Integer.class);
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		if (count == 0) {
			return false;
		}
		return true;
	}

	public boolean passwordCheck(String userData, String password, int checkType) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select count(*) from userdetail where");
		int count = 0;
		switch (checkType) {
		case 1:
			stringBuilder.append("email =? and password = ?");
			break;
		case 2:
			stringBuilder.append("mobileNumber = ? and password = ?");
			break;
		}
		String sql = stringBuilder.toString();
		try {
			count = jdbcTemplate.queryForObject(sql, new Object[] { userData, password }, Integer.class);
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		if (count == 0) {
			return false;
		}
		return true;
	}

	public int updateMobileNumber(int userId, String mobileNo) {

		String sql = "Update userdetail SET mobileNumber = ? where userId = ?";
		try {
			return jdbcTemplate.update(sql, new Object[] { mobileNo, userId });
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	public int updateMobileNumber(String email, String mobileNo) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update userdetail set mobileNo = ? where email = ?");
		String sql = stringBuilder.toString();
		try {
			return jdbcTemplate.update(sql, new Object[] { mobileNo, email });
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	public int updatePassword(String userData, String newPassword, int updateType) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update userdetail set password = ? where");
		switch (updateType) {
		case 1:
			stringBuilder.append("email=?");
			break;
		case 2:
			stringBuilder.append("mobileNumber=?");
			break;
		}
		String sql = stringBuilder.toString();
		try {
			return jdbcTemplate.update(sql, new Object[] { newPassword, userData });
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	public int resetPassword(String userData, String oldPassword, String newPassword, int resetType) {
		return 0;
	}

	public int addAccount(UserDetail user) throws Exception {
		try {
			String sql = "insert into userdetail (`firstname`, `lastname`, `email`, `mobileNumber`, `createdDate`, `modifiedDate`, `password`) values(?,?,?,?,?,?,?)";
			return jdbcTemplate.update(sql,
					new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(), user.getMobileNumber(),
							new java.util.Date().toString(), new java.util.Date().toString(), user.getPassword() });
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	public int deleteAccount(int userId) {
		return 0;
	}

	public int deleteAccount(String userData, int flagType) {
		return 0;
	}

}
