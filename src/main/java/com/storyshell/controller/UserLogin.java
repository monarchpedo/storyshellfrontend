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

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;

import com.storyshell.model.LoginCredential;
import com.storyshell.services.ILoginService;

/**
 * @author santoshkumar
 *
 */
@Path("/user")
public class UserLogin {
	
	@Autowired
	ILoginService loginService;
	
	@POST
	@Path(value = "/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewUser(@Valid LoginCredential loginDetail){
		return loginService.processLogin(loginDetail);
	}
	
	@GET
	@Path("/forgotpassword/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgotPassword(@PathParam("email") @Email String email) throws MessagingException{
		return loginService.processForgetPassword(email);
	}
	
	@GET
	@Path("/verifyresetpassword/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyResetPassword(@PathParam("key") String key) throws MessagingException{
		return loginService.processVerifyResetpassword(key);
	}
	
}
