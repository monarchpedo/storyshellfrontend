package com.storyshell.controller;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.storyshell.model.UserDetail;

/**
 * @author santoshkumar
 *
 */
@Path("/user")
public class CreateUser {
	
	@POST
	@Path(value = "/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewUser(@Valid UserDetail userDetail){
		
		return Response.status(Response.Status.OK).entity(userDetail).type(MediaType.APPLICATION_JSON).build();
		
	}
	

}
