package com.storyshell.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author santoshkumar
 *
 */
public class UserDetail implements Serializable {

	private static final long serialVersionUID = -5042418818239722422L;

	@NotNull
	@Size(min = 2, max = 25, message = "firstName Length should be between 2 and 25")
	private String firstName;
	@NotNull
	@Size(min = 2, max = 25, message = "firstName Length should be between 2 and 25")
	private String lastName;
	private int userId;
	@NotNull
	@Pattern(message = "Invalid Email Address->"
			+ "Valid emails:user@gmail.com or my.user@domain.com etc.", regexp = "^[a-zA-Z0-9_!#$%&�*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	@NotNull
	@Size(min = 10, max = 15, message = "Mobile number should be valid")
	private String mobileNumber;
	@NotNull
	@Size(min = 8, max = 15, message = "Password must be in between 8 to 15")
	private String password;
	@NotNull
	private String dob;
	@NotNull
	private String gender;
	private String createdTime;
	private String modifiedTime;

	public UserDetail() {
		super();
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDetail [firstName=").append(firstName).append(", lastName=").append(lastName)
				.append(", userId=").append(userId).append(", email=").append(email).append(", mobileNumber=")
				.append(mobileNumber).append(",dob=").append(dob).append(", createdTime=").append(createdTime)
				.append(", modifiedTime=").append(modifiedTime).append("]");
		return builder.toString();
	}

}
