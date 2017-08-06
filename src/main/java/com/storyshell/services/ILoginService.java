package com.storyshell.services;

import javax.ws.rs.core.Response;

import com.storyshell.model.LoginCredential;

/**
 * @author santoshkumar
 *
 */
public interface ILoginService {

	public Response processLogin(LoginCredential loginDetails);
}
