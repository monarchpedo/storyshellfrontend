package com.storyshell.services;

import javax.mail.MessagingException;
import javax.ws.rs.core.Response;

import com.storyshell.model.LoginCredential;

/**
 * @author santoshkumar
 *
 */
public interface ILoginService {

	public Response processLogin(LoginCredential loginDetails) throws Exception;
	
	public Response processForgetPassword(String email) throws MessagingException;
	
	public Response processVerifyResetpassword(String key);

    public Response checkUser(int userId);
}
