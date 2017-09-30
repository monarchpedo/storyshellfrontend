package com.storyshell.serviceImpl;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.xml.ws.soap.AddressingFeature.Responses;

import org.springframework.data.redis.core.HashOperations;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.dao.RedisRepository;
import com.storyshell.model.Location;
import com.storyshell.model.ProfileModel;
import com.storyshell.services.ProfileService;
import com.storyshell.util.Constants;
import com.storyshell.util.ResponseGenerator;

public class ProfileServiceImpl implements ProfileService {

	@Inject
	private RedisRepository redisUtility;
	@Inject
	private AuthenticationDao authDao;

	@Override
	public Response addProfile(int userId, ProfileModel profileModel) {
		if (authDao.isUserExists(userId)) {
			int result = authDao.addProfile(userId, profileModel);
			if (result == 1) {
				ProfileModel model = authDao.getprofile(userId);
				redisUtility.saveObject(Constants.profile.PROFILE_REDIS_KEY, String.valueOf(userId), model);
				return ResponseGenerator.generateResponse(model, Response.Status.ACCEPTED);
			} else {
				return ResponseGenerator.generateResponse("profile is not added, please try again..",
						Response.Status.NO_CONTENT);
			}
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);
	}

	@Override
	public Response updateProfile(int userId, ProfileModel profileModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateProfileImage(int userId, String profileImage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addLocation(int userId, Location loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateLocation(int userId, Location loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteLocation(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getProfile(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteprofile(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response isActiveprofile(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getLocation(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
