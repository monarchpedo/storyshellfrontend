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
		if(rs.getInt("idUserDetail")!=0){
		userDetail.setIdUserDetail(rs.getInt("idUserDetail"));
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
		
		if(!StringUtils.isEmpty(rs.getString("mobile_number"))){
			userDetail.setMobileNumber(rs.getString("mobile_number"));
		}
		
		if(!StringUtils.isEmpty(rs.getString("created_date"))){
			userDetail.setCreatedTime(rs.getString("created_date"));
		}
		
		if(!StringUtils.isEmpty(rs.getString("modified_date"))){
		userDetail.setModifiedTime(rs.getString("modified_date"));
		}
		
		return userDetail;
	}

}
