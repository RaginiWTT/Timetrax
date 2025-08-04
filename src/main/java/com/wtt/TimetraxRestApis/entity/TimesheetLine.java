package com.wtt.TimetraxRestApis.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TimesheetLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LineId")
	private Integer lineId;

	@ManyToOne
	@JoinColumn(name = "TimesheetId", nullable = false)
	private Timesheet timesheet;

	@ManyToOne
	@JoinColumn(nullable = false, name = "ProjectId")
	private Project project;

	@ManyToOne
	@JoinColumn(nullable = false, name = "TaskId")
	private ProjectTask task;

	@Column(nullable = false, name = "Status")
	private String status;

	@Column(name = "CreatedBy")
	private Integer createdBy;
	@Column(name = "CreatedDateTime")
	private LocalDateTime createdDateTime;

	@Column(name = "ModifiedBy")
	private Integer modifiedBy;
	@Column(name = "ModifiedDateTime")
	private LocalDateTime modifiedDateTime;

	@OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
	private List<TimesheetLineHour> hours = new ArrayList<TimesheetLineHour>();

	// always comes from frontend

//    @PrePersist
//    public void prePersist() {

//        this.createdDateTime = LocalDateTime.now();
//    }

	@PreUpdate
	public void preUpdate() {
		this.modifiedDateTime = LocalDateTime.now();
	}

	public TimesheetLine() {

	}

	public TimesheetLine(Integer lineId, Timesheet timesheet, Project projectId, ProjectTask taskId, String status,
			Integer createdBy, LocalDateTime createdDateTime, Integer modifiedBy, LocalDateTime modifiedDateTime,
			List<TimesheetLineHour> hours) {
		super();
		this.lineId = lineId;
		this.timesheet = timesheet;
		this.project = projectId;
		this.task = taskId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDateTime = createdDateTime;
		this.modifiedBy = modifiedBy;
		this.modifiedDateTime = modifiedDateTime;
		this.hours = hours;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Timesheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectTask getTask() {
		return task;
	}

	public void setTask(ProjectTask task) {
		this.task = task;
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

	public List<TimesheetLineHour> getHours() {
		return hours;
	}

	public void setHours(List<TimesheetLineHour> hours) {
		this.hours = hours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
