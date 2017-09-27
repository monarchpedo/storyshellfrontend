package com.storyshell.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private static Logger LOG = LoggerFactory.getLogger(GenericExceptionHandler.class); 

	public GenericExceptionHandler(String message) {
		this.message = message;
	}
	
	public Response toResponse(RuntimeException exception) {
		return Response.serverError().entity(exception.getMessage()).build();
	}
	
	public String getMessage() {
		return this.message;
	}
}
