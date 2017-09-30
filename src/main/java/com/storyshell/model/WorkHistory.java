package com.storyshell.model;

import java.io.Serializable;

public class WorkHistory  implements Serializable {

	/**
	 * @author MonarchPedo
	 */
	private static final long serialVersionUID = -2201433282412142778L;
    
	private String startDate;
    private String endDate;
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
	
}
