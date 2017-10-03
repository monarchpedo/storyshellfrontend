package com.storyshell.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import com.storyshell.dao.AuthenticationDao;
import com.storyshell.dao.AuthenticationDaoImpl;

/**
 * *
 * 
 * @author Monarchpedo
 *
 * @param <T>
 */

public class ExecutorServiceCallable<T> implements Callable<T> {

	private int userId;
	private String methodName;
	private AuthenticationDao authDb;

	public ExecutorServiceCallable(int userId, String methodName, AuthenticationDao authDb) {
		this.methodName = methodName;
		this.userId = userId;
		this.authDb = authDb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T call() {
		Method method = null;
		T result = null;
		try {
			method = this.authDb.getClass().getDeclaredMethod(methodName, int.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new GenericExceptionHandler(e.getMessage());
		} catch (SecurityException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		try {
			result = (T) method.invoke(this.authDb, userId);
		} catch (IllegalAccessException e) {
			throw new GenericExceptionHandler(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new GenericExceptionHandler(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new GenericExceptionHandler(e.getMessage());
		}
		return result;
	}

}
