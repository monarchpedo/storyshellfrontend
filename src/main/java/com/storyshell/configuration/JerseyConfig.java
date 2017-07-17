package com.storyshell.configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

import com.storyshell.controller.AuthenticationController;

@Configuration
@ApplicationPath("api/v1")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

	}

	@PostConstruct
	public void init() {
		register(AuthenticationController.class);
		register(GenericExceptionMapper.class);
		register(RequestContextFilter.class);
	}
}
