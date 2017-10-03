package com.storyshell.util;

/**
 * @author Monarchpedo
 * storyshell.com
 * copyright protected 
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

	/**
	 * It return the mapped value of a class object.
	 */
	@Override
	public T mapRow(ResultSet rs, int row) throws SQLException {
		List<String> fieldsList = getModelFields(this.classObject);

		Class<? extends Object> object = this.classObject.getClass();
		for (String field : fieldsList) {
			if (!field.equals("serialVersionUID")) {
				try {
					setFields(rs, field);
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

	/**
	 * It return the list of fields of class model
	 */
	protected List<String> getModelFields(T a) {
		List<String> listOfClassFields = new ArrayList<String>();
		this.className = a.getClass().getSimpleName();
		Field[] fields = a.getClass().getDeclaredFields();
		for (Field field : fields) {
			listOfClassFields.add(field.getName());
		}
		return listOfClassFields;
	}

	/**
	 * @author monarchpedo
	 */
	protected void setFields(ResultSet rs, String field) throws NoSuchMethodException, SecurityException, SQLException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String functionName = prefix + field.substring(0, 1).toUpperCase() + field.substring(1, field.length());
		String getReturnValue = "get" + field.substring(0, 1).toUpperCase() + field.substring(1, field.length());

		if (this.classObject.getClass().getDeclaredMethod(getReturnValue).getReturnType().equals(Integer.TYPE)) {
			if (rs.getInt(field) != 0) {

				this.classObject.getClass().getDeclaredMethod(functionName, int.class).invoke(this.classObject,
						rs.getInt(field));
			}
		} else if (this.classObject.getClass().getDeclaredMethod(getReturnValue).getReturnType().equals(Float.TYPE)) {

			this.classObject.getClass().getDeclaredMethod(functionName, float.class).invoke(this.classObject,
					rs.getFloat(field));

		} else if (this.classObject.getClass().getDeclaredMethod(getReturnValue).getReturnType().equals(Long.TYPE)) {

			this.classObject.getClass().getDeclaredMethod(functionName, long.class).invoke(this.classObject,
					rs.getLong(field));

		} else if (this.classObject.getClass().getDeclaredMethod(getReturnValue).getReturnType()
				.equals(Timestamp.class)) {

			this.classObject.getClass().getDeclaredMethod(functionName, java.sql.Timestamp.class)
					.invoke(this.classObject, rs.getTimestamp(field));

		} else if (this.classObject.getClass().getDeclaredMethod(getReturnValue).getReturnType().equals(Date.class)) {

			this.classObject.getClass().getDeclaredMethod(functionName, Date.class).invoke(this.classObject,
					rs.getDate(field));

		} else if (this.classObject.getClass().getDeclaredMethod(getReturnValue).getReturnType().equals(Boolean.TYPE)) {

			this.classObject.getClass().getDeclaredMethod(functionName, boolean.class).invoke(this.classObject,
					rs.getBoolean(field));
		} else if (this.classObject.getClass().getDeclaredMethod(getReturnValue).getReturnType().equals(String.class)) {

			if (!StringUtils.isEmpty(rs.getString(field))) {
				this.classObject.getClass().getDeclaredMethod(functionName, String.class).invoke(this.classObject,
						rs.getString(field));
			}
		}

	}

}
