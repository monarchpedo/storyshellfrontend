package com.storyshell.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	private static Logger LOG = LoggerFactory.getLogger(GenericExceptionMapper.class);

	public Response toResponse(Throwable exception) {

		LOG.error(exception.getMessage());
		return Response.serverError().entity(exception.getMessage()).build();
	}

}
