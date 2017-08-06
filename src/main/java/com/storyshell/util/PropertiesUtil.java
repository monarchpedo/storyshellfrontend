package com.storyshell.util;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

/**
 * @author santoshkumar
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:project.properties" })
@Component
public class PropertiesUtil {

	@Inject
	private Environment env;
	
	public String getProperty(String key){
		return env.getProperty(key);
	}
	
   public String getProperty(String key, String defaultValue){
		String value = env.getProperty(key);
		if(StringUtils.isEmpty(value)){
			return defaultValue;
		}
		return value;
	}
}
