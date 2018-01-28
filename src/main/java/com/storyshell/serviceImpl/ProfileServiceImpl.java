package com.storyshell.serviceImpl;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.dao.ElasticData;
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
 * @category service implementation
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	@Inject
	private RedisRepository redisUtility;
	@Inject
	private AuthenticationDao authDao;

	@Inject
	private ElasticData elasticData;
	private ExecutorService executor = Executors.newFixedThreadPool(100);

	@Override
	public Response addProfile(ProfileModel profileModel) throws ParseException {
		if (authDao.isUserExists(profileModel.getUserId())) {
			int result = authDao.addProfile(profileModel);
			if (result == 1) {
				ProfileModel model = authDao.getProfile(profileModel.getUserId());

				// after saving profile in database we are saving data to
				// elasticsearch database
				ProfileModel resultModel = elasticData.save(model);

				return ResponseGenerator.generateResponse(model, Response.Status.ACCEPTED);
			} else {
				return ResponseGenerator.generateResponse("profile is not added, please try again..",
						Response.Status.NO_CONTENT);
			}
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);
	}

	@Override
	public Response updateProfile(int userId, ProfileModel profileModel) throws ParseException {
		if (authDao.isUserExists(userId)) {
			int result = authDao.updateProfile(userId, profileModel);
			if (result == 1) {
				ProfileModel model = authDao.getProfile(userId);
				redisUtility.updateObject(Constants.profile.PROFILE_REDIS_KEY, String.valueOf(userId), model);

				// after updating data to mysql making changes on elasticsearch
				// database

				ProfileModel updateModel = elasticData.save(model);

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
				ProfileModel model = authDao.getProfile(userId);
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
				Location location = authDao.getLocation(userId);
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
				Location location = authDao.getLocation(userId);
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
				return ResponseGenerator.generateResponse(profile, Response.Status.OK);
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
			Location loc = authDao.getLocation(userId);
			if (loc != null) {
				return ResponseGenerator.generateResponse(loc, Response.Status.ACCEPTED);
			} else {
				return ResponseGenerator.generateResponse("profile is not added, please try again..",
						Response.Status.NO_CONTENT);
			}
		}
		return ResponseGenerator.generateResponse("userid does not exist", Response.Status.NOT_FOUND);
	}

	/**
	 * it just return profiledata with all proper content in itself
	 */
	private ProfileModel getProfileData(int userId) throws InterruptedException, ExecutionException {
		ProfileModel profile = null;
		Location loc;
		UserDetail userDetail = null;

		Future[] futureTask = new Future[3];
		String FieldNames[] = { "getProfile", "getUserDetail", "getLocation" };
		futureTask[0] = executor.submit(new ExecutorServiceCallable<ProfileModel>(userId, "getProfile", authDao));
		futureTask[1] = executor.submit(new ExecutorServiceCallable<UserDetail>(userId, "getUserDetail", authDao));
		futureTask[2] = executor.submit(new ExecutorServiceCallable<Location>(userId, "getLocation", authDao));

		profile = (ProfileModel) futureTask[0].get();
		userDetail = (UserDetail) futureTask[1].get();
		loc = (Location) futureTask[2].get();

		profile.setLoc(loc);
		profile.setUserDetail(userDetail);
		return profile;
	}

}
