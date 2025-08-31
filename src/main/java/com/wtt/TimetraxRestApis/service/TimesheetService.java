package com.wtt.TimetraxRestApis.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.wtt.TimetraxRestApis.dto.TimesheetApprovalResponseDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetResponseDTO;
import com.wtt.TimetraxRestApis.entity.Timesheet;

public interface TimesheetService {
	
	Timesheet saveTimesheet(Timesheet timesheet);
	List<TimesheetResponseDTO> getTimehseetByStartDateAndStatusId(LocalDate localDate, Integer statusId);
	
	TimesheetApprovalResponseDTO  approveTimesheet(Integer timesheetId, Integer statusId, Integer approvedBy, Integer active);
	
	Boolean checkExistsByResourceIdAndWeekStartAndWeekEndDate(Integer resourceId, LocalDate weekStartDate, LocalDate weekEndDate);


	/**
	 * Retrieves a Timesheet by its ID.
	 * 
	 * @param id the ID of the Timesheet
	 * @return the Timesheet with the specified ID
	 */

}
