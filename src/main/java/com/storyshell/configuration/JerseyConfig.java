package com.storyshell.configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

import com.storyshell.controller.AuthenticationController;
import com.storyshell.controller.CreateUser;
import com.storyshell.util.GenericExceptionMapper;
import com.storyshell.util.ValidationExceptionMapper;

@Configuration
@ApplicationPath("api/v1")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

	}

	@PostConstruct
	public void init() {
		register(AuthenticationController.class);
		register(CreateUser.class);
		register(GenericExceptionMapper.class);
		register(RequestContextFilter.class);
		register(ValidationExceptionMapper.class);
	}
}
