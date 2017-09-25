package com.storyshell.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.storyshell.model.UserDetail;

/*
 * @author Monarchpedo
 * copyright 2017 @stroyshell Licences
 * */
public class QueryMapper<T extends Object> {
	
	public static String modelName;
	private List<Object> list;

	
	/**
	 * @author Moanrchpedo constructor for QueryMapper
	 */
	public QueryMapper() {
		list = new ArrayList<Object>();
	}
	
	

	/*
	 * @Monarchpedo return the list of fields in class T
	 */
	public List<String> getFieldsName(T a) throws ClassNotFoundException {
		Field[] fields = a.getClass().getDeclaredFields();
		List<String> fieldNames = new ArrayList<String>();

		for (Field f : fields) {
			fieldNames.add(f.getName());
		}
		return fieldNames;

	}
	
	

	/*
	 * @Monarchpedpo Return the list of parameter neccessary in insert,update
	 * query
	 */
	public List<String> getColumnList(T a) throws ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		List<String> fieldNames = this.getFieldsName(a);
		List<String> listOfQueryParameter = new ArrayList<String>();
		String prefix = "get";
		for (String field : fieldNames) {
			String functionName = prefix + field.substring(0, 1).toUpperCase() + field.substring(1, field.length());
			if (!field.equals("serialVersionUID")) {
				// System.out.println(a.getClass().getDeclaredMethod(functionName).getReturnType());
				// System.out.println(a.getClass().getDeclaredMethod(functionName).invoke(a));
				if (a.getClass().getDeclaredMethod(functionName).getReturnType().equals(Integer.TYPE)) {
					Integer result = (Integer) a.getClass().getDeclaredMethod(functionName).invoke(a);
					if (result.intValue() != 0) {
						listOfQueryParameter.add(field);
						list.add(result);
					}

				} else {
					if (!StringUtils.isEmpty(a.getClass().getDeclaredMethod(functionName).invoke(a))) {
						listOfQueryParameter.add(field);
						list.add(a.getClass().getDeclaredMethod(functionName).invoke(a));
					}
				}
			}
		}
		return listOfQueryParameter;
	}
	
	

	/*
	 * @Monarchpedo return the queryString
	 */
	public String getInsertQuery(T a, String tableName) throws ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<String> listofFields = this.getColumnList(a);
		StringBuilder columnName = new StringBuilder();
		StringBuilder valueName = new StringBuilder();
		columnName.append("Insert into ").append(tableName).append("( ");
		valueName.append("Values(");
		for (String field : listofFields) {
			columnName.append(field).append(",");
			valueName.append("?,");
		}
		String part1 = columnName.toString().substring(0, columnName.toString().length() - 1);
		String part2 = valueName.toString().substring(0, valueName.toString().length() - 1);
		String result = part1 + ") " + part2 + ")";
		return result;
	}

	
	
	/*
	 * @author Monarchpedo return the updareQuery
	 **/
	public String getUpdateQuery(T a, String tableName) throws ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		StringBuilder updateQuery = new StringBuilder();
		updateQuery.append("Update ").append(tableName).append(" SET ");
		List<String> listofFields = this.getColumnList(a);
		for (String field : listofFields) {
			updateQuery.append(field).append("= ?,");
		}
		String result = updateQuery.toString().substring(0, updateQuery.toString().length() - 1);
		return result;
	}
	
	

	/*
	 * @author Monarchpedo return the object values need to save or update in
	 * database
	 */
	public Object[] getObjectValues() {
		Object object[] = new Object[list.size()];

		for (int i = 0; i < list.size(); i++) {
			object[i] = list.get(i);
		}
		return object;
	}

	
	
	public static void main(String args[]) throws ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		UserDetail userDetail = new UserDetail();
	    userDetail.setFirstName("raja");
		userDetail.setUserId(123);
		userDetail.setLastName("bose");
		QueryMapper<UserDetail> queryMapper = new QueryMapper<UserDetail>();
		String query = queryMapper.getInsertQuery(userDetail, "userdetail");
		// String updateQuery = queryMapper.getUpdateQuery(post,"userpost");
		Object[] list = queryMapper.getObjectValues();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
		System.out.println(query);
		// System.out.println(updateQuery);
	}

}

