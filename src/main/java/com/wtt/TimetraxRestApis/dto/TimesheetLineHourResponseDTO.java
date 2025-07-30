package com.wtt.TimetraxRestApis.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;


public class TimesheetLineHourResponseDTO {
    private Integer hourId;
    private LocalDate weekDate;
    private BigDecimal workingHours_Billable;
    private String notes;
    
    // No Args Constructor
	public TimesheetLineHourResponseDTO() {
	}
    
	public TimesheetLineHourResponseDTO(Integer hourId, LocalDate weekDate, BigDecimal workingHours_Billable,
			String notes) {
		this.hourId = hourId;
		this.weekDate = weekDate;
		this.workingHours_Billable = workingHours_Billable;
		this.notes = notes;
	}

	public Integer getHourId() {
		return hourId;
	}

	public void setHourId(Integer hourId) {
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
    
}

