package com.storyshell.model;

import java.io.Serializable;

import javax.validation.constraints.Null;

public class Location implements Serializable {
	/**
	 * @author Wicky
	 */
	private static final long serialVersionUID = -187319718195612648L;
	private String userId;
	@Null
	private String city;
	@Null
	private String country;
	@Null
	private String locality;
	@Null
	private String state;
	private int workId;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Location [city=").append(city).append(", country=").append(country).append(",locality=")
				.append(locality).append(",state=").append(state).append("]");
		return result.toString();

	}

}
