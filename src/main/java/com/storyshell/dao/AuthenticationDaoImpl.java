package com.storyshell.dao;

import java.io.Console;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.storyshell.model.Location;
import com.storyshell.model.ProfileModel;
import com.storyshell.model.UserDetail;
import com.storyshell.util.Constants;
import com.storyshell.util.CustomRowMapper;
import com.storyshell.util.GenericExceptionHandler;
import com.storyshell.util.GenericRowMapper;
import com.storyshell.util.QueryMapper;

/**
 * @author santoshkumar Monarchpedo
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

	public int updatePassword(int userId, String newPassword) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update userdetail set password = ? where");
		stringBuilder.append("userId=?");
		String sql = stringBuilder.toString();
		try {
			return jdbcTemplate.update(sql, new Object[] { newPassword, userId });
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	public int resetPassword(String userData, String oldPassword, String newPassword, int resetType) {
		return 0;
	}

	public int addAccount(UserDetail user) throws Exception {
		try {
			String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
			java.util.Date date = Constants.OUT_DATETIME_FORMAT.parse(dateTime);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			String sql = "insert into userdetail (`firstname`, `lastname`, `email`, `mobileNumber`,`dob`, `gender` ,`createdDate`, `modifiedDate`, `password`) values(?,?,?,?,?,?,?,?,?)";
			return jdbcTemplate.update(sql, new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(),
					user.getMobileNumber(), user.getDob(), user.getGender(), sqlDate, sqlDate, user.getPassword() });
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

	@Override
	public int addProfile(int userId, ProfileModel profile) throws ParseException {
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		java.util.Date date = Constants.OUT_DATETIME_FORMAT.parse(dateTime);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		Map<String, Object> mapList = new HashMap<String, Object>();
		mapList.put("createdDate", sqlDate);
		mapList.put("modifiedDate", sqlDate);
		mapList.put("userId", userId);
		QueryMapper<ProfileModel> queryMapper = new QueryMapper<ProfileModel>();
		String insertQuery = queryMapper.getInsertQuery(profile, "profile", mapList);
		Object[] values = queryMapper.getObjectValues();
		try {
			return jdbcTemplate.update(insertQuery, values);
		} catch (RuntimeException exception) {
			throw new GenericExceptionHandler(exception.getMessage());
		}
	}

	@Override
	public int deleteProfile(int userId) {
		String sql = "Delete from profile where userId = ?";
		try {
			return jdbcTemplate.update(sql, new Object[] { userId });
		} catch (RuntimeException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}

	}

	@Override
	public int updateProfileImage(int userId, String profileImageLoc) {
		String sql = "update profile set profileImage = ? where userId = ?";
		try {
			return jdbcTemplate.update(sql, new Object[] { profileImageLoc, userId });
		} catch (RuntimeException exception) {
			throw new GenericExceptionHandler(exception.getMessage());
		}
	}

	@Override
	public int updateProfile(int userId, ProfileModel profile) throws ParseException {
		String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
		java.util.Date date = Constants.OUT_DATETIME_FORMAT.parse(dateTime);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		Map<String, Object> mapList = new HashMap<String, Object>();
		mapList.put("modifiedDate", sqlDate);
		QueryMapper<ProfileModel> queryMapper = new QueryMapper<ProfileModel>();
		String updateQuery = queryMapper.getUpdateQuery(profile, "profile", mapList);
		Object[] values = queryMapper.getObjectValues();
		String sql = updateQuery + "where userId = " + userId;
		try {
			return jdbcTemplate.update(sql, values);
		} catch (RuntimeException exception) {
			throw new GenericExceptionHandler(exception.getMessage());
		}
	}

	@Override
	public int addLocation(int userId, Location loc) {
		String sql = "update `location` (`userId`,`city`,`locality`,`state`,`country` values(?,?,?,?,?)";
		try {
			return jdbcTemplate.update(sql,
					new Object[] { userId, loc.getCity(), loc.getLocality(), loc.getState(), loc.getCountry() });
		} catch (RuntimeException exception) {
			throw new GenericExceptionHandler(exception.getMessage());
		}
	}

	@Override
	public int updateLocation(int userId, Location loc) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		QueryMapper<Location> queryMapper = new QueryMapper<Location>();
		String updateQuery = queryMapper.getUpdateQuery(loc, "location", mapList);
		String sql = updateQuery + "where userId =" + userId;
		Object[] values = queryMapper.getObjectValues();
		try {
			return jdbcTemplate.update(sql, values);
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

	@Override
	public ProfileModel getprofile(int userId) {
		String sql = "select * from profile where userId = " + userId;
		ProfileModel profileModel = null;
		try {
			profileModel = jdbcTemplate.queryForObject(sql, new GenericRowMapper<ProfileModel>(profileModel));
		} catch (RuntimeException exception) {
			throw new GenericExceptionHandler(exception.getMessage());
		}
		return profileModel;
	}

	@Override
	public List<ProfileModel> getProfileList(String interestSection) {

		return null;
	}

	@Override
	public Location getLcoation(int userId) {
		String sql = "select * from location where userId = " + userId;
		Location location = null;
		try {
			location = jdbcTemplate.queryForObject(sql, new GenericRowMapper<Location>(location));
		} catch (RuntimeException exception) {
			throw new GenericExceptionHandler(exception.getMessage());
		}
		return location;
	}

	@Override
	public boolean isActiveProfile(int userId) {
		String sql = "select count(*) from profile where userId= ? and status='active'";
		try {
			int userCount = jdbcTemplate.queryForObject(sql, new Object[] { userId }, Integer.class);
			if (userCount == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
	}

}
