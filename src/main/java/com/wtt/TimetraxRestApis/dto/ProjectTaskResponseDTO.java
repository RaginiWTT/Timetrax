package com.wtt.TimetraxRestApis.dto;

public class ProjectTaskResponseDTO {
    private Integer taskId;
    private String taskName;
    private Boolean active;
    private Integer projectId;
    private String projectName;

    // Getters, setters, constructors
    public ProjectTaskResponseDTO() {}

	public ProjectTaskResponseDTO(Integer taskId, String taskName, Boolean active, Integer projectId,
			String projectName) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.active = active;
		this.projectId = projectId;
		this.projectName = projectName;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
