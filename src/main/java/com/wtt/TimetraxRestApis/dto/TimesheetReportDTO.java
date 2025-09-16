package com.wtt.TimetraxRestApis.dto;

import java.math.BigDecimal;
import java.util.List;

public class TimesheetReportDTO {
	
	private Integer timesheetId;
	private Integer resourceId;
	private String resourceName;
	private Integer projectId;
	private String projectName;
	private Integer taskId;
	private String taskName;
	private String weekStartDate;
	private String weekEndDate;
	private BigDecimal totalHours;
	private Integer statusId;
	private String statusName;
	
	private List<TimesheetResponseLineDTO> timeshhetLineDTO;
	
	
	public List<TimesheetResponseLineDTO> getTimeshhetLineDTO() {
		return timeshhetLineDTO;
	}
	public void setTimeshhetLineDTO(List<TimesheetResponseLineDTO> timeshhetLineDTO) {
		this.timeshhetLineDTO = timeshhetLineDTO;
	}
	public TimesheetReportDTO(Integer timesheetId, Integer resourceId, String resourceName, Integer projectId,
			String projectName, Integer taskId, String taskName, String weekStartDate, String weekEndDate,
			BigDecimal totalHours, Integer statusId, String statusName, List<TimesheetResponseLineDTO> timeshhetLineDTO) {
		super();
		this.timesheetId = timesheetId;
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.projectId = projectId;
		this.projectName = projectName;
		this.taskId = taskId;
		this.taskName = taskName;
		this.weekStartDate = weekStartDate;
		this.weekEndDate = weekEndDate;
		this.totalHours = totalHours;
		this.statusId = statusId;
		this.statusName = statusName;
		this.timeshhetLineDTO = timeshhetLineDTO;
	}
	public TimesheetReportDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
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
	public String getWeekStartDate() {
		return weekStartDate;
	}
	public void setWeekStartDate(String weekStartDate) {
		this.weekStartDate = weekStartDate;
	}
	public String getWeekEndDate() {
		return weekEndDate;
	}
	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}



}
