package com.wtt.TimetraxRestApis.dto;

import lombok.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetResponseLineDTO {
    private Integer lineId;
    private Integer timesheetId;
    private Integer taskId;
    private String taskName; // <-- additional field from Task
    private Integer projectId;
    private String projectName; // <-- additional field from Project
    private List<TimesheetLineHourResponseDTO> hours;
    
    // No Args Constructor
        public TimesheetResponseLineDTO() {	
        	
        }
    
    // All Args Constructor
	public TimesheetResponseLineDTO(Integer lineId, Integer timesheetId, Integer taskId, String taskName,
			Integer projectId, String projectName, List<TimesheetLineHourResponseDTO> hours) {
		super();
		this.lineId = lineId;
		this.timesheetId = timesheetId;
		this.taskId = taskId;
		this.taskName = taskName;
		this.projectId = projectId;
		this.projectName = projectName;
		this.hours = hours;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Integer getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(Integer timesheetId) {
		this.timesheetId = timesheetId;
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public List<TimesheetLineHourResponseDTO> getHours() {
		return hours;
	}

	public void setHours(List<TimesheetLineHourResponseDTO> hours) {
		this.hours = hours;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}


