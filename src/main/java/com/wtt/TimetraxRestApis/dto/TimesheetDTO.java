package com.wtt.TimetraxRestApis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TimesheetDTO {
	private Integer resourceId;
	private Integer createdBy;
	private LocalDateTime createdDateTime;
	private Integer submittedBy;
	private Integer statusId; // Assuming statusId is needed
	
	private LocalDate weekStartDate;
    private LocalDate weekEndDate;
    
    private Integer timesheetId;
    private BigDecimal totalHours;
    
    
	
    public Integer getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(Integer timesheetId) {
		this.timesheetId = timesheetId;
	}

	public BigDecimal getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(BigDecimal totalHours) {
		this.totalHours = totalHours;
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


    
	private List<TimesheetLineDTO> lines = new ArrayList<TimesheetLineDTO>();
	// Add other fields as needed

	public TimesheetDTO() {

	}

	public TimesheetDTO(Integer resourceId, Integer createdBy, LocalDateTime createdDateTime, Integer submittedBy,Integer statusId,LocalDate weekStartDate,LocalDate weekEndDate,
			Integer timesheetId,BigDecimal totalHours ,List<TimesheetLineDTO> lines) {
		super();
		this.resourceId = resourceId;
		this.createdBy = createdBy;
		this.createdDateTime = createdDateTime;
		this.submittedBy = submittedBy;
		this.statusId = statusId; // Initialize statusId
		this.weekStartDate = weekStartDate;
		this.weekEndDate= weekEndDate;
		this.timesheetId = timesheetId;
		this.totalHours = totalHours;
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
