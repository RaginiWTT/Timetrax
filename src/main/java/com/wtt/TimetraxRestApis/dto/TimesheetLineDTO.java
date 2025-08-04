package com.wtt.TimetraxRestApis.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimesheetLineDTO {
	private Integer projectId;
	private Integer taskId;
	private String status;
	private Integer createdBy;
	private LocalDateTime createdDateTime;
	private Integer modifiedBy;
	private LocalDateTime modifiedDateTime;
	private List<TimesheetHourDTO> hours = new ArrayList<TimesheetHourDTO>();

	public TimesheetLineDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimesheetLineDTO(Integer projectId, Integer taskId, String status, Integer createdBy,
			LocalDateTime createdDateTime, Integer modifiedBy, LocalDateTime modifiedDateTime,
			List<TimesheetHourDTO> hours) {
		super();
		this.projectId = projectId;
		this.taskId = taskId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDateTime = createdDateTime;
		this.modifiedBy = modifiedBy;
		this.modifiedDateTime = modifiedDateTime;
		this.hours = hours;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public List<TimesheetHourDTO> getHours() {
		return hours;
	}

	public void setHours(List<TimesheetHourDTO> hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "TimesheetLineDTO [projectId=" + projectId + ", taskId=" + taskId + ", status=" + status + ", createdBy="
				+ createdBy + ", createdDateTime=" + createdDateTime + ", modifiedBy=" + modifiedBy
				+ ", modifiedDateTime=" + modifiedDateTime + ", hours=" + hours + "]";
	}

}