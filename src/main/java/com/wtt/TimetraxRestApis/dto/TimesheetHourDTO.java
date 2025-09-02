package com.wtt.TimetraxRestApis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimesheetHourDTO {
	private LocalDate weekDate;
	private BigDecimal workingHours_Billable;
	private BigDecimal workingHours_NotBillable;
	private String notes;
	private Integer createdBy;
	private LocalDateTime createdDateTime;
	private Integer hourId;
	
	public Integer getHourId() {
		return hourId;
	}

	public void setHourId(Integer hourId) {
		this.hourId = hourId;
	}

	public TimesheetHourDTO() {

	}

	public TimesheetHourDTO(LocalDate weekDate, BigDecimal workingHours_Billable, BigDecimal workingHours_NotBillable,
			String notes, Integer createdBy, LocalDateTime createdDateTime, Integer hourId) {
		super();
		this.weekDate = weekDate;
		this.workingHours_Billable = workingHours_Billable;
		this.workingHours_NotBillable = workingHours_NotBillable;
		this.notes = notes;
		this.createdBy = createdBy;
		this.createdDateTime = createdDateTime;
		this.hourId = hourId;
	}

	public LocalDate getWeekDate() {
		return weekDate;
	}

	public void setWeekDate(LocalDate weekDate) {
		this.weekDate = weekDate;
	}

	public BigDecimal getWorkingHours_Billable() {
		return workingHours_Billable;
	}

	public void setWorkingHours_Billable(BigDecimal workingHours_Billable) {
		this.workingHours_Billable = workingHours_Billable;
	}

	public BigDecimal getWorkingHours_NotBillable() {
		return workingHours_NotBillable;
	}

	public void setWorkingHours_NotBillable(BigDecimal workingHours_NotBillable) {
		this.workingHours_NotBillable = workingHours_NotBillable;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	@Override
	public String toString() {
		return "TimesheetHourDTO [weekDate=" + weekDate + ", workingHours_Billable=" + workingHours_Billable
				+ ", workingHours_NotBillable=" + workingHours_NotBillable + ", notes=" + notes + ", createdBy="
				+ createdBy + ", createdDateTime=" + createdDateTime + "]";
	}

}
