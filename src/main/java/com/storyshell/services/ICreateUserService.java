package com.storyshell.services;

import javax.ws.rs.core.Response;

import com.storyshell.model.UserDetail;

public interface ICreateUserService {

	public Response processCreateUser(UserDetail userDetail);
}
