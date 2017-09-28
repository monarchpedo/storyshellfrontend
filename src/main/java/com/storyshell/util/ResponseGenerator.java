package com.storyshell.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author santoshkumar
 *
 */
public class ResponseGenerator {

	public static Response generateResponse(Object object,Status statusCode){
		ObjectMapper mapper = new ObjectMapper();
		Response response = null;
		try {
			response = Response.status(statusCode).entity(mapper.writeValueAsString(object)).type(MediaType.APPLICATION_JSON).build();
		} catch (JsonProcessingException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return response;
	}
}
