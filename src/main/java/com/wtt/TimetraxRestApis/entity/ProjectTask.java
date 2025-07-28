package com.wtt.TimetraxRestApis.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskId")
    private Integer taskId;

    @Column(length = 100, nullable = false, name = "TaskName")
    private String taskName;

    @Column(nullable = false, name = "Active")
    private Boolean active;

    @Column(name = "CreatedBy")
    private Integer createdBy;
    @Column(name = "CreatedDateTime")
    private LocalDateTime createdDateTime;
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;
    @Column(name = "ModifiedDateTime")
    private LocalDateTime modifiedDateTime;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectId", nullable = false)
    private Project project;

    // --- Constructors ---
    public ProjectTask() {}

    public ProjectTask(Integer taskId, String taskName, Boolean active, Integer createdBy,
                       LocalDateTime createdDateTime, Integer modifiedBy,
                       LocalDateTime modifiedDateTime, Project project) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.active = active;
        this.createdBy = createdBy;
        this.createdDateTime = createdDateTime;
        this.modifiedBy = modifiedBy;
        this.modifiedDateTime = modifiedDateTime;
        this.project = project;
    }

    // --- Getters and Setters ---
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    @PrePersist
    protected void onCreate() {
        createdDateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDateTime = LocalDateTime.now();
    }

}

