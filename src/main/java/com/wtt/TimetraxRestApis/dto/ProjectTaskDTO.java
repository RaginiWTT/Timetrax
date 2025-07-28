package com.wtt.TimetraxRestApis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectTaskDTO {

    private String taskName;
    private Boolean active;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
// These fields are marked as WRITE_ONLY to prevent them from being serialized in responses
    private Integer createdBy;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer modifiedBy;
    private Integer projectId;

    // --- Constructors ---
    public ProjectTaskDTO() {}

    public ProjectTaskDTO(String taskName, Boolean active, Integer createdBy, Integer modifiedBy, Integer projectId) {
        this.taskName = taskName;
        this.active = active;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.projectId = projectId;
    }

    // --- Getters and Setters ---
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}

