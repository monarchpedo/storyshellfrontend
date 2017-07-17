package com.storyshell.configuration;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		return Response.serverError().entity(exception.getMessage()).build();
	}

}
