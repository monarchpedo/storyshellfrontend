package com.storyshell.util;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import com.storyshell.dao.AuthenticationDao;
/**
 * *
 * @author Monarchpedo
 *
 * @param <T>
 */

public class ExecutorServiceCallable<T> implements Callable<T> {

	private int userId; 
	private String methodName;
	@Inject
	private AuthenticationDao authDb;
	
    public  ExecutorServiceCallable(int userId,String methodName) {
	   this.methodName = methodName;
	   this.userId = userId;
    }
	@Override
	public T call() throws Exception {
		Method  method  =  this.authDb.getClass().getDeclaredMethod(methodName, Integer.class);
		return (T) method.invoke(this.authDb, userId);
	}

}
