package com.wtt.TimetraxRestApis.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimesheetDTO {
	private Integer resourceId;
	private Integer createdBy;
	private LocalDateTime createdDateTime;
	private Integer submittedBy;
	private Integer statusId; // Assuming statusId is needed
	private List<TimesheetLineDTO> lines = new ArrayList<TimesheetLineDTO>();
	// Add other fields as needed

	public TimesheetDTO() {

	}

	public TimesheetDTO(Integer resourceId, Integer createdBy, LocalDateTime createdDateTime, Integer submittedBy,Integer statusId,
			List<TimesheetLineDTO> lines) {
		super();
		this.resourceId = resourceId;
		this.createdBy = createdBy;
		this.createdDateTime = createdDateTime;
		this.submittedBy = submittedBy;
		this.statusId = statusId; // Initialize statusId
		this.lines = lines;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
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

	public Integer getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(Integer submittedBy) {
		this.submittedBy = submittedBy;
	}

	public List<TimesheetLineDTO> getLines() {
		return lines;
	}

	public void setLines(List<TimesheetLineDTO> lines) {
		this.lines = lines;
	}
	
	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "TimesheetDTO [resourceId=" + resourceId + ", createdBy=" + createdBy + ", createdDateTime="
				+ createdDateTime + ", submittedBy=" + submittedBy + ", lines=" + lines + "]";
	}

}
