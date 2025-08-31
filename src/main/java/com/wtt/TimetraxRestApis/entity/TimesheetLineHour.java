package com.wtt.TimetraxRestApis.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class TimesheetLineHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HourId")
    private Integer hourId;

    @ManyToOne
    @JoinColumn(name = "LineId", nullable = false)
    private TimesheetLine line;

    @Column(nullable = false, name = "WeekDate")
    private LocalDate weekDate;

    @Column(precision = 18, scale = 2, nullable = false, name = "WorkingHours_Billable")
    private BigDecimal workingHours_Billable = BigDecimal.ZERO;

    @Column(precision = 18, scale = 2, nullable = false, name = "WorkingHours_NotBillable")
    private BigDecimal workingHours_NotBillable = BigDecimal.ZERO;

    @Column(nullable = false, name = "Notes")
    private String notes;

    @Column(name = "CreatedBy")
    private Integer createdBy;
    @Column(name = "CreatedDateTime")
    private LocalDateTime createdDateTime;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;
    @Column(name = "ModifiedDateTime")
    private LocalDateTime modifiedDateTime;

    @PrePersist
    public void prePersist() {
        this.createdDateTime = LocalDateTime.now();
    }
    
    
    

    @PreUpdate
    public void preUpdate() {
        this.modifiedDateTime = LocalDateTime.now();
    }

    
	public TimesheetLineHour() {
		
	}
	
	
	public TimesheetLineHour(LocalDate weekDate, BigDecimal workingHours_Billable, BigDecimal workingHours_NotBillable,
			String notes, Integer createdBy, LocalDateTime createdDateTime) {
		super();
		this.weekDate = weekDate;
		this.workingHours_Billable = workingHours_Billable;
		this.workingHours_NotBillable = workingHours_NotBillable;
		this.notes = notes;
		this.createdBy = createdBy;
		this.createdDateTime = createdDateTime;
	}

	public Integer getHourId() {
		return hourId;
	}

	public void setHourId(Integer hourId) {
		this.hourId = hourId;
	}

	public TimesheetLine getLine() {
		return line;
	}

	public void setLine(TimesheetLine line) {
		this.line = line;
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
    
    
}

