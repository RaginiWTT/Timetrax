package com.wtt.TimetraxRestApis.dto;


import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;


@Builder
public class AssignResourceDTO {
	private Integer id;
    private Integer projectId;
    private Integer resourceId;
    private LocalDate fromDate;
    private LocalDate toDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer assignedBy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer modifiedBy;
    
    String ProjectName;
    String ResourceName;
    
	public AssignResourceDTO() {
		super();
	}
	
	public AssignResourceDTO(Integer id,Integer projectId, Integer resourceId, LocalDate fromDate, LocalDate toDate,
			Integer assignedBy, Integer modifiedBy,String projectName, String resourceName) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.resourceId = resourceId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.assignedBy = assignedBy;
		this.modifiedBy = modifiedBy;
		this.ProjectName = projectName;
		this.ResourceName = resourceName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Integer getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(Integer assignedBy) {
		this.assignedBy = assignedBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "AssignResourceDTO [projectId=" + projectId + ", resourceId=" + resourceId + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", assignedBy=" + assignedBy + ", modifiedBy=" + modifiedBy + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		this.ProjectName = projectName;
	}

//
	public String getResourceName() {
		return ResourceName;
	}

	public void setResourceName(String resourceName) {
		this.ResourceName = resourceName;
	}
    
}

