package com.storyshell.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.storyshell.model.UserDetail;
import com.storyshell.services.ICreateUserService;

/**
 * @author santoshkumar
 *
 */
@Path("/create")
public class CreateUser {
	
	@Autowired
	ICreateUserService createUserService;
	
	@POST
	@Path(value = "/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewUser(@Valid UserDetail userDetail) throws MessagingException{
		return createUserService.processCreateUser(userDetail);
	}
	
	@GET
	@Path("/account-activation/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgotPassword(@PathParam("key") String key) throws Exception{
		return createUserService.processVerifyUser(key);
	}
	
}
