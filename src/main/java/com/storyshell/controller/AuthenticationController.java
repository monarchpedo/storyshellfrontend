package com.storyshell.controller;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import com.storyshell.model.UserDetail;
import com.storyshell.services.AuthenticationService;

@Component
@Path("/users")
public class AuthenticationController {

	@Inject
	private AuthenticationService authService;

	@GET
	@Path("/userdetail")
	@Produces("application/json")
	public Response getUserDetail() throws SQLException {
		UserDetail userDetail = authService.getUserAccount();
		if(userDetail==null){
			Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(200).entity(userDetail).build();
	}
	
	@GET
	@Path("/name")
	public String getName(){
		String s = "raja";
		return s;
	}
}
