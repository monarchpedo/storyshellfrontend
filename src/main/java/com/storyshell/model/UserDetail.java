package com.storyshell.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * @author santoshkumar
 *
 */
@Component("userDetail")
public class UserDetail implements Serializable{

	private static final long serialVersionUID = -5042418818239722422L;
	private String firstName;
	private String lastName;
	private int    userId;
	private String email;
	private String mobileNumber;
	private String createdTime;
	private String modifiedTime;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int idUserDetail) {
		this.userId = idUserDetail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDetail [firstName=").append(firstName)
				.append(", lastName=").append(lastName).append(", userId=")
				.append(userId).append(", email=").append(email)
				.append(", mobileNumber=").append(mobileNumber)
				.append(", createdTime=").append(createdTime)
				.append(", modifiedTime=").append(modifiedTime).append("]");
		return builder.toString();
	}

}
