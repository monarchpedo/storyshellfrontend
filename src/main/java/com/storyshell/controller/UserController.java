package com.storyshell.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.storyshell.model.Location;
import com.storyshell.model.ProfileModel;
import com.storyshell.services.ProfileService;

/**
 * @author monarchpedo
 *
 */

@Path("/users")
public class UserController {

	@Inject
	private ProfileService profileService;

	@POST
	@Path(value = "/{userId}/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfile(ProfileModel profile, @PathParam("userId") int userId) {
		return profileService.addProfile(userId, profile);
	}

	@POST
	@Path(value = "/{userId}/location/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLocation(Location loc, @PathParam("userId") int userId) {
		return profileService.addLocation(userId, loc);
	}

	@PUT
	@Path(value = "/{userId}/profile/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProfile(ProfileModel profile, @PathParam("userId") int userId) {
		return profileService.updateProfile(userId, profile);
	}

	@PUT
	@Path(value = "/{userId}/location/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateLocation(Location loc, @PathParam("userId") int userId) {
		return profileService.updateLocation(userId, loc);
	}

	@GET
	@Path("/{userId}/profile/status")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfileStatus(@PathParam("userId") int userId) {
		return profileService.isActiveprofile(userId);
	}

	@GET
	@Path("/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@QueryParam("userId") int userId) {
		return profileService.getProfile(userId);
	}

	@GET
	@Path("/location")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfileLocation(@QueryParam("userId") int userId) {
		return profileService.getLocation(userId);
	}
}
