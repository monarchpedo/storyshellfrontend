package com.storyshell.services;

import javax.mail.MessagingException;
import javax.ws.rs.core.Response;

import com.storyshell.model.UserDetail;

public interface ICreateUserService {

	public Response processCreateUser(UserDetail userDetail) throws MessagingException;
	public Response processVerifyUser(String key) throws Exception;
}
