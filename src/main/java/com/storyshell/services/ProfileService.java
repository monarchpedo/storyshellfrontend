package com.storyshell.services;

import java.text.ParseException;

import javax.ws.rs.core.Response;

import com.storyshell.model.Location;
import com.storyshell.model.ProfileModel;

public interface ProfileService {
    
	public Response addProfile(ProfileModel profileModel) throws ParseException;
	
	public Response updateProfile(int userId,ProfileModel profileModel) throws ParseException;
	
	public Response updateProfileImage(int userId,String profileImage);
	
	public Response addLocation(int userId, Location loc);
	
	public Response updateLocation(int userId, Location loc);
	
	public Response deleteLocation(int userId);
	
	public Response getProfile(int userId);
	
	public Response deleteprofile(int userId);
	
	public Response isActiveprofile(int userId);
	
	public Response getLocation(int userId);
	
}
