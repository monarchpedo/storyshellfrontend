package com.storyshell.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

import com.storyshell.model.UserDetail;

public class CustomRowMapper implements RowMapper<UserDetail>{

	@Override
	public UserDetail mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		UserDetail userDetail = new UserDetail();
		if(rs.getInt("userId")!=0){
		userDetail.setUserId(rs.getInt("userId"));
		}
		if(!StringUtils.isEmpty(rs.getString("firstname"))){
		userDetail.setFirstName(rs.getString("firstname"));
		}
		
		if(!StringUtils.isEmpty(rs.getString("email"))){
		userDetail.setEmail(rs.getString("email"));
		}
       
		if(!StringUtils.isEmpty(rs.getString("lastname"))){
			userDetail.setLastName(rs.getString("lastname"));
		}
		
		if(!StringUtils.isEmpty(rs.getString("mobilenumber"))){
			userDetail.setMobileNumber(rs.getString("mobilenumber"));
		}
		
		if(!StringUtils.isEmpty(rs.getString("userId"))){
			userDetail.setCreatedTime(rs.getString("createddate"));
		}
		
		if(!StringUtils.isEmpty(rs.getString("modifieddate"))){
		userDetail.setModifiedTime(rs.getString("modifieddate"));
		}
		
		return userDetail;
	}

}
