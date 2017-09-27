package com.storyshell.util;

/*
 * @author Monarchpedo
 * Stroyshell.com private Licences
 * Copyright is prohibited
 * */
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;



public class GenericRowMapper<T extends Object> implements RowMapper<T> {

	private String className;
	private T classObject;
	private String prefix = "set";

	public GenericRowMapper(T a) {
		this.classObject = a;
	}

	/*
	 * @Author MonarchPedo It return the mapped value of a class object.
	 **/
	@Override
	public T mapRow(ResultSet rs, int row) throws SQLException {
		List<String> fieldsList = getModelFields(this.classObject);

		Class<? extends Object> object = this.className.getClass();
		for (String field : fieldsList) {
			if (!field.equals("serialVersionUID")) {
				try {
					setFields(object, rs, field);
				} catch (NoSuchMethodException e) {
					throw new GenericExceptionHandler(e.getMessage());
				} catch (SecurityException e) {
					throw new GenericExceptionHandler(e.getMessage());
				} catch (IllegalAccessException e) {
					throw new GenericExceptionHandler(e.getMessage());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					throw new GenericExceptionHandler(e.getMessage());
				} catch (InvocationTargetException e) {
					throw new GenericExceptionHandler(e.getMessage());
				}

			}
		}
		return this.classObject;
	}

	/*
	 * @author Monarchpedo It return the list of fields of class model
	 **/
	protected List<String> getModelFields(T a) {
		List<String> listOfClassFields = new ArrayList<String>();
		this.className = a.getClass().getSimpleName();
		Field[] fields = this.className.getClass().getDeclaredFields();
		for (Field field : fields) {
			listOfClassFields.add(field.getName());
		}
		return listOfClassFields;
	}

	/*
	 * @ MonarchPedo It just set field of particular field
	 */
	protected void setFields(Class object, ResultSet rs, String field) throws NoSuchMethodException, SecurityException,
			SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String functionName = prefix + field.substring(0, 1).toUpperCase() + field.substring(1, field.length());
		Object returnType = object.getClass().getDeclaredMethod(functionName).getReturnType();

		if (returnType.equals(Integer.TYPE)) {
			if (rs.getInt(field) != 0) {
				
				object.getClass().getDeclaredMethod(functionName).invoke(object, rs.getInt(field));
			}
		} else if (returnType.equals(Float.TYPE)) {
			
			object.getClass().getDeclaredMethod(functionName).invoke(object, rs.getFloat(field));

		} else if (returnType.equals(Long.TYPE)) {
			
			object.getClass().getDeclaredMethod(functionName).invoke(object, rs.getLong(field));
		
		} else if (returnType.equals(Timestamp.class)) {
			
			object.getClass().getDeclaredMethod(functionName).invoke(object, rs.getTimestamp(field));
		
		} else if (returnType.equals(Date.class)) {
			
			object.getClass().getDeclaredMethod(functionName).invoke(object, rs.getDate(field));
		
		} else if (returnType.equals(Boolean.TYPE)) {
			
			object.getClass().getDeclaredMethod(functionName).invoke(object, rs.getBoolean(field));
		} else {
		
			if (!StringUtils.isEmpty(rs.getString(field))) {
				object.getClass().getDeclaredMethod(functionName).invoke(object, rs.getString(field));
			}
		}

	}

}

