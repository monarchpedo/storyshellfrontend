package com.storyshell.serviceImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.dao.RedisRepository;
import com.storyshell.model.Location;
import com.storyshell.model.ProfileModel;
import com.storyshell.model.UserDetail;
import com.storyshell.services.ProfileService;
import com.storyshell.util.Constants;
import com.storyshell.util.ExecutorServiceCallable;
import com.storyshell.util.GenericExceptionHandler;
import com.storyshell.util.ResponseGenerator;
/**
 * @author Monarchpedo
 * storyshell.com
 * copyright protected Licences 
 * */

public class ProfileServiceImpl implements ProfileService {

	@Inject
	private RedisRepository redisUtility;
	@Inject
	private AuthenticationDao authDao;
	private ExecutorService executor = Executors.newFixedThreadPool(100);

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
		if (authDao.isUserExists(userId)) {
			int result = authDao.updateProfile(userId, profileModel);
			if (result == 1) {
				ProfileModel model = authDao.getprofile(userId);
				redisUtility.updateObject(Constants.profile.PROFILE_REDIS_KEY, String.valueOf(userId), model);
				return ResponseGenerator.generateResponse(model, Response.Status.ACCEPTED);
			} else {
				return ResponseGenerator.generateResponse("profile is not added, please try again..",
						Response.Status.NO_CONTENT);
			}
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);
	}

	@Override
	public Response updateProfileImage(int userId, String profileImage) {
		if (authDao.isUserExists(userId)) {
			int result = authDao.updateProfileImage(userId, profileImage);
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
	public Response addLocation(int userId, Location loc) {
		if (authDao.isUserExists(userId)) {
			int result = authDao.addLocation(userId, loc);
			if (result == 1) {
				ProfileModel profile = (ProfileModel) redisUtility.findKey(Constants.profile.PROFILE_REDIS_KEY,
						String.valueOf(userId));
				Location location = authDao.getLcoation(userId);
				profile.setLoc(location);
				redisUtility.updateObject(Constants.profile.PROFILE_REDIS_KEY, String.valueOf(userId), profile);
				return ResponseGenerator.generateResponse(profile, Response.Status.ACCEPTED);
			} else {
				return ResponseGenerator.generateResponse("profile is not added, please try again..",
						Response.Status.NO_CONTENT);
			}
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);
	}

	@Override
	public Response updateLocation(int userId, Location loc) {
		if (authDao.isUserExists(userId)) {
			int result = authDao.updateLocation(userId, loc);
			if (result == 1) {
				ProfileModel profile = (ProfileModel) redisUtility.findKey(Constants.profile.PROFILE_REDIS_KEY,
						String.valueOf(userId));
				Location location = authDao.getLcoation(userId);
				profile.setLoc(location);
				redisUtility.updateObject(Constants.profile.PROFILE_REDIS_KEY, String.valueOf(userId), profile);
				return ResponseGenerator.generateResponse(profile, Response.Status.ACCEPTED);
			} else {
				return ResponseGenerator.generateResponse("profile is not added, please try again..",
						Response.Status.NO_CONTENT);
			}
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);
	}

	@Override
	public Response deleteLocation(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getProfile(int userId) {
		if (authDao.isUserExists(userId)) {
			ProfileModel profile = (ProfileModel) redisUtility.findKey(Constants.profile.PROFILE_REDIS_KEY,
					String.valueOf(userId));
			if (profile == null) {
				try {
					profile = getProfileData(userId);
				} catch (InterruptedException e) {
					throw new GenericExceptionHandler(e.getMessage());
				} catch (ExecutionException e) {
					throw new GenericExceptionHandler(e.getMessage());
				}
				redisUtility.saveObject(Constants.profile.PROFILE_REDIS_KEY, String.valueOf(userId), profile);
				return ResponseGenerator.generateResponse(profile, Response.Status.ACCEPTED);
			} else {
				return ResponseGenerator.generateResponse(profile, Response.Status.ACCEPTED);
			}
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);
	}

	@Override
	public Response deleteprofile(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response isActiveprofile(int userId) {
		if (authDao.isUserExists(userId)) {
			boolean isActive = authDao.isActiveProfile(userId);
			ResponseGenerator.generateResponse(isActive, Response.Status.ACCEPTED);
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);	
	}

	@Override
	public Response getLocation(int userId) {
		if (authDao.isUserExists(userId)) {
			Location loc = authDao.getLcoation(userId);
			if (loc != null) {
				return ResponseGenerator.generateResponse(loc, Response.Status.ACCEPTED);
			} else {
				return ResponseGenerator.generateResponse("profile is not added, please try again..",
						Response.Status.NO_CONTENT);
			}
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);
	}

	private ProfileModel getProfileData(int userId) throws InterruptedException, ExecutionException {
		ProfileModel profile = null;
		Location loc;
		UserDetail userDetail = null;
		// List<Future> futureTaskList = new ArrayList<Future>();
		Future[] futureTask = new Future[3];
		String FieldNames[] = { "getProfileName", "getUserDetail", "getLocation" };
		futureTask[0] = executor.submit(new ExecutorServiceCallable<ProfileModel>(userId, "getProfileName"));
		futureTask[1] = executor.submit(new ExecutorServiceCallable<UserDetail>(userId, "getUserDetail"));
		futureTask[2] = executor.submit(new ExecutorServiceCallable<Location>(userId, "getLocation"));
		// for (int i = 0; i < 3; i++) {
		// futureTaskList.add(executor.submit(new
		// ExecutorServiceCallable<ProfileModel>(userId, FieldNames[i])));
		// }

		profile = (ProfileModel) futureTask[0].get();
		userDetail = (UserDetail) futureTask[1].get();
		loc = (Location) futureTask[2].get();

		profile.setLoc(loc);
		profile.setUserDetail(userDetail);
		return profile;
	}

}
