package com.storyshell.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * author @Raja Bose
 * software developer
 *
 */
@SpringBootConfiguration
@ComponentScan(basePackages = { "com.storyshell" })
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Order(1)
public class App implements WebApplicationInitializer
{
    public static void main( String[] args )
    {	
    	SpringApplication.run(App.class, args);
    }

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		// TODO Auto-generated method stub
		sc.setInitParameter("contextConfigLocation", "noop");

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		sc.addListener(new ContextLoaderListener(context));
		sc.addListener(new RequestContextListener());		
	}
}
