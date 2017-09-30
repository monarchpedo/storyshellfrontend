package com.storyshell.model;

import java.io.Serializable;
import java.util.List;

public class ProfileModel implements Serializable {
	/**
	 * @author Monarchpedo
	 */
	private static final long serialVersionUID = 3053947430126009626L;
	private int userId;
	private String profileLink;
	private String profileImage;
	private String description;
	private int status;
	private String tags;
	private String modifiedDate;
	private String createdDate;
	private Location loc;
	private UserDetail userDetail;
	private WorkHistory workHistory;

	public String getProfileLink() {
		return profileLink;
	}

	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public WorkHistory getWorkHistory() {
		return workHistory;
	}

	public void setWorkHistory(WorkHistory workHistory) {
		this.workHistory = workHistory;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
