package com.storyshell.dao;

import java.text.ParseException;

import com.storyshell.util.Constants;

public class check {
     public static void main(String args[]) throws ParseException{
    	String dateTime = Constants.OUT_DATETIME_FORMAT.format(new java.util.Date());
 		java.util.Date date = Constants.OUT_DATETIME_FORMAT.parse(dateTime);
 		java.sql.Date sqlDate = new java.sql.Date(Long.parseLong(dateTime));
        System.out.println(sqlDate);
     }
}
