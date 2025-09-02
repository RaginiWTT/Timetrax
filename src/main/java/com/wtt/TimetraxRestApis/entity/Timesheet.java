package com.wtt.TimetraxRestApis.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TimesheetId")
    private Integer timesheetId;

    
    @ManyToOne
    @JoinColumn(nullable = false, name = "ResourceId")
    private Resource resourceId;

    @Column(nullable = false, name = "WeekStartDate")
    private LocalDate weekStartDate;

    @Column(nullable = false, name = "WeekEndDate")
    private LocalDate weekEndDate;

    @Column(name = "CreatedBy")
    private Integer createdBy;
    @Column(name = "CreatedDateTime")
    private LocalDateTime createdDateTime;

    @Column(name = "SubmittedBy")
    private Integer submittedBy;
    @Column(name = "SubmittedDateTime")
    private LocalDateTime submittedDateTime;

    @Column(name = "ApprovedBy")
    private Integer approvedBy;
    @Column(name = "ApprovedDateTime")
    private LocalDateTime approvedDateTime;
    
    @Column(name = "StatusId", columnDefinition = "int default 1")
    private Integer statusId;
    
//    @Column(name = "TotalHours")
//    private Double hours;
//    
//    
//
//
//
//
//	public Double getHours() {
//		return hours;
//	}
//
//
//
//
//	public void setHours(Double hours) {
//		this.hours = hours;
//	}




	public List<TimesheetLine> getLines() {
		return lines;
	}




	public void setLines(List<TimesheetLine> lines) {
		this.lines = lines;
	}


	@Column(precision = 18, scale = 2,	 nullable = false, name = "TotalHours")
    private BigDecimal totalHours = BigDecimal.ZERO;

    // Relationships
    @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL)
    private List<TimesheetLine> lines = new ArrayList<>();

    // Automatically set dates for new entity
    @PrePersist
    public void prePersist() {
//        this.weekStartDate = LocalDate.now().with(DayOfWeek.MONDAY);
//        this.weekEndDate = LocalDate.now().with(DayOfWeek.SUNDAY);
       // submittedDateTime = LocalDateTime.now();
        this.createdDateTime = LocalDateTime.now();
    }
    
 
    

    public Timesheet() {
		
	}




	public Timesheet(Integer timesheetId, Resource resourceId, LocalDate weekStartDate, LocalDate weekEndDate,
			Integer createdBy, LocalDateTime createdDateTime, Integer submittedBy, LocalDateTime submittedDateTime,
			Integer approvedBy, LocalDateTime approvedDateTime,Integer statusId ,BigDecimal totalHours, List<TimesheetLine> lines) {
		super();
		this.timesheetId = timesheetId;
		this.resourceId = resourceId;
		this.weekStartDate = weekStartDate;
		this.weekEndDate = weekEndDate;
		this.createdBy = createdBy;
		this.createdDateTime = createdDateTime;
		this.submittedBy = submittedBy;
		this.submittedDateTime = submittedDateTime;
		this.approvedBy = approvedBy;
		this.approvedDateTime = approvedDateTime;
		this.statusId = statusId;
		this.totalHours = totalHours;
		this.lines = lines;
	}




	@PreUpdate
    protected void onUpdate() {
        approvedDateTime = LocalDateTime.now();
    }

	public Integer getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(Integer timesheetId) {
		this.timesheetId = timesheetId;
	}

	public Resource getResourceId() {
		return resourceId;
	}

	public void setResourceId(Resource resourceId) {
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

	public LocalDateTime getSubmittedDateTime() {
		return submittedDateTime;
	}

	public void setSubmittedDateTime(LocalDateTime submittedDateTime) {
		this.submittedDateTime = submittedDateTime;
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

	public List<TimesheetLine> getTimesheetLines() {
		return lines;
	}

	public void setTimesheetLines(List<TimesheetLine> timesheetLines) {
		this.lines = timesheetLines;
	}



	@Override
	public String toString() {
		return "Timesheet [timesheetId=" + timesheetId + ", resourceId=" + resourceId + ", weekStartDate="
				+ weekStartDate + ", weekEndDate=" + weekEndDate + ", createdBy=" + createdBy + ", createdDateTime="
				+ createdDateTime + ", submittedBy=" + submittedBy + ", submittedDateTime=" + submittedDateTime
				+ ", approvedBy=" + approvedBy + ", approvedDateTime=" + approvedDateTime + ", totalHours=" + totalHours
				+ ", lines=" + lines + "]";
	}




	public Integer getStatusId() {
		// TODO Auto-generated method stub
		return statusId;
	}
	

    public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	
	
    
    
}

