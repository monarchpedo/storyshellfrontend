package com.storyshell.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author Santosh Monarchpedo
 * */

public final class Constants {
	
	public static DateFormat OUT_DATETIME_FORMAT = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
	 
	public static class UserDetails{
		
		public static final String FIRST_NAME = "firstName";
		public static final String LAST_NAME = "lastName";
		public static final String MOBILE_NUMBER = "mobileNnumber";
		public static final String EMAIL = "email";
		public static final String REDIS_KEY_CREATE = "createUser";
		public static final String REDIS_KEY_FORGET_PASS = "forgetpassword";
	}
	
	public static class Common{
		public static final String KEY = "enc_desc_key";
		public static final String DEFAULT_KEY = "asdf";
	}
	
	public static class profile{
		public static final String PROFILE_REDIS_KEY = "profile";
		
	}
	
}
