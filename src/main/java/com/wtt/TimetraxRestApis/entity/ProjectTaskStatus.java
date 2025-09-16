package com.wtt.TimetraxRestApis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProjectTaskStatus {
	
	@Id
	private Integer statusId;
	private String statusName;
	
	public ProjectTaskStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProjectTaskStatus(Integer statusId, String statusName) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	
	
	

}
