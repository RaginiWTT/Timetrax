package com.wtt.TimetraxRestApis.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;


public class TimesheetResponseDTO {
	private Integer timesheetId;
	private Integer resourceId;
	private LocalDate weekStartDate;
	private LocalDate weekEndDate;

	private Integer approvedBy;
	private LocalDateTime approvedDateTime;
	private BigDecimal totalHours;
	private Integer statusId;
	private String status;
	private Integer submittedBy;
	private LocalDateTime submittedDateTime;
	
	private String resourceName;
	
	
	
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public TimesheetResponseDTO() {
		
	}


	public TimesheetResponseDTO(Integer timesheetId, Integer resourceId, LocalDate weekStartDate, LocalDate weekEndDate,
			Integer approvedBy, LocalDateTime approvedDateTime, BigDecimal totalHours, Integer statusId, String status,
			Integer submittedBy, LocalDateTime submittedDateTime) {
		super();
		this.timesheetId = timesheetId;
		this.resourceId = resourceId;
		this.weekStartDate = weekStartDate;
		this.weekEndDate = weekEndDate;
		this.approvedBy = approvedBy;
		this.approvedDateTime = approvedDateTime;
		this.totalHours = totalHours;
		this.statusId = statusId;
		this.status = status;
		this.submittedBy = submittedBy;
		this.submittedDateTime = submittedDateTime;
	}


	public Integer getTimesheetId() {
		return timesheetId;
	}


	public void setTimesheetId(Integer timesheetId) {
		this.timesheetId = timesheetId;
	}


	public Integer getResourceId() {
		return resourceId;
	}


	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}


	public LocalDate getWeekStartDate() {
		return weekStartDate;
	}


	public void setWeekStartDate(LocalDate weekStartDate) {
		this.weekStartDate = weekStartDate;
	}


	public LocalDate getWeekEndDate() {
		return weekEndDate;
	}


	public void setWeekEndDate(LocalDate weekEndDate) {
		this.weekEndDate = weekEndDate;
	}


	public Integer getApprovedBy() {
		return approvedBy;
	}


	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}


	public LocalDateTime getApprovedDateTime() {
		return approvedDateTime;
	}


	public void setApprovedDateTime(LocalDateTime approvedDateTime) {
		this.approvedDateTime = approvedDateTime;
	}


	public BigDecimal getTotalHours() {
		return totalHours;
	}


	public void setTotalHours(BigDecimal totalHours) {
		this.totalHours = totalHours;
	}


	public Integer getStatusId() {
		return statusId;
	}


	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getSubmittedBy() {
		return submittedBy;
	}


	public void setSubmittedBy(Integer submittedBy) {
		this.submittedBy = submittedBy;
	}


	public LocalDateTime getSubmittedDateTime() {
		return submittedDateTime;
	}


	public void setSubmittedDateTime(LocalDateTime submittedDateTime) {
		this.submittedDateTime = submittedDateTime;
	}
	
	
	

}
