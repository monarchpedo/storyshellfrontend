package com.storyshell.model;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Monarchpedo
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Document(indexName = "profile", type = "profile", shards = 1, replicas = 0, refreshInterval = "-1")
public class ProfileModel implements Serializable {
	/**
	 * @author Monarchpedo
	 */
	private static final long serialVersionUID = 3053947430126009626L;
    @Id
	private int profileId;
	@NotNull
	private int userId;
	@NotNull
	private String profileLink;
	@NotNull
	private String profileImage;
	private String description;
	private int status;
	private String tags;
	private Date modifiedDate;
	private Date createdDate;
	@Field( type = FieldType.Object )
	private Location loc;
	@Field( type = FieldType.Object )
	private UserDetail userDetail;
	@Field( type = FieldType.Object )
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

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(" ProfileModel [").append("profileId=").append(profileId).append(",userId=").append(userId)
				.append(",profileLink=").append(profileLink).append(", profileImage=").append(profileImage)
				.append(",description=").append(description).append(", status=").append(status)
				.append(", modifiedDate=").append(modifiedDate).append(", createdDate=").append(createdDate)
				.append(", tags=").append(tags);
		if (loc != null) {
			result.append(", loc=").append(loc.toString());
		}
		if (userDetail != null) {
			result.append(", userDetail=").append(userDetail.toString());
		}
		if (workHistory != null) {
			result.append(", workHistory=").append(workHistory.toString());
		}
		return result.toString();
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

}
