package com.storyshell.model;

import java.io.Serializable;
import java.util.List;

public class ProfileModel implements Serializable {
	private int userId;
	private String shortHandName;
	private String profileUrlId;
	private String profileImageLoc;
	private Location loc;
	private UserDetail userDetail;
	private int active;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getShortHandName() {
		return shortHandName;
	}

	public void setShortHandName(String shortHandName) {
		this.shortHandName = shortHandName;
	}

	public String getProfileUrlId() {
		return profileUrlId;
	}

	public void setProfileUrlId(String pageLinkUrl) {
		this.profileUrlId = pageLinkUrl;
	}

	public String getProfileImageLoc() {
		return profileImageLoc;
	}

	public void setProfileImageLoc(String profileImageLocation) {
		this.profileImageLoc = profileImageLocation;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<String> getInterestedSections() {
		return interestedSections;
	}

	public void setInterestedSections(List<String> interestedSections) {
		this.interestedSections = interestedSections;
	}

	List<String> interestedSections;
}
