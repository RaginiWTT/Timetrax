package com.wtt.TimetraxRestApis.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "AssignResource")
@Builder
public class AssignResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer projectId;

    @Column(nullable = false)
    private Integer resourceId;

    @Column(nullable = false)
    private LocalDate fromDate;

    private LocalDate toDate;

    @Column(nullable = false)
    private Integer assignedBy;

    @Column(updatable = false, insertable = false)
    private LocalDateTime assignedDatetime;

    private Integer modifiedBy;

    @Column(insertable = false)
    private LocalDateTime modifiedDateTime;

	public AssignResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssignResource( Integer projectId, Integer resourceId, LocalDate fromDate, LocalDate toDate,
			Integer assignedBy, LocalDateTime assignedDatetime, Integer modifiedBy, LocalDateTime modifiedDateTime) {
		super();
	
		this.projectId = projectId;
		this.resourceId = resourceId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.assignedBy = assignedBy;
		this.assignedDatetime = assignedDatetime;
		this.modifiedBy = modifiedBy;
		this.modifiedDateTime = modifiedDateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Integer getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(Integer assignedBy) {
		this.assignedBy = assignedBy;
	}

	public LocalDateTime getAssignedDatetime() {
		return assignedDatetime;
	}

	public void setAssignedDatetime(LocalDateTime assignedDatetime) {
		this.assignedDatetime = assignedDatetime;
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

	@Override
	public String toString() {
		return "AssignResource [id=" + id + ", projectId=" + projectId + ", resourceId=" + resourceId + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", assignedBy=" + assignedBy + ", assignedDatetime="
				+ assignedDatetime + ", modifiedBy=" + modifiedBy + ", modifiedDateTime=" + modifiedDateTime + "]";
	}
    
    
    
}
