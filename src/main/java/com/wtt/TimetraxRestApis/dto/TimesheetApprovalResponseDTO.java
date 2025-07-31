package com.wtt.TimetraxRestApis.dto;

public class TimesheetApprovalResponseDTO {
	private Integer timesheetId;
	private String message;
	private boolean approved;

	// No Args Constructor
	public TimesheetApprovalResponseDTO() {
	}

	// All Args Constructor
	public TimesheetApprovalResponseDTO(Integer timesheetId, String message, boolean approved) {
		super();
		this.timesheetId = timesheetId;
		this.message = message;
		this.approved = approved;
	}

	public Integer getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(Integer timesheetId) {
		this.timesheetId = timesheetId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}
