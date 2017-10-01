package com.storyshell.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class WorkHistory implements Serializable {

	/**
	 * @author MonarchPedo
	 */
	private static final long serialVersionUID = -2201433282412142778L;

	@NotNull
	private String startDate;
	@NotNull
	private String endDate;
	@NotNull
	private String workDesignation;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getWorkDesignation() {
		return workDesignation;
	}

	public void setWorkDesignation(String workDesignation) {
		this.workDesignation = workDesignation;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("WorkHistory [").append("startDate =").append(startDate).append(", endDate =").append(endDate)
				.append(", workDesignation=").append(workDesignation).append("]");
		return result.toString();
	}

}
