package com.wtt.TimetraxRestApis.service;

import com.wtt.TimetraxRestApis.entity.Timesheet;

public interface TimesheetService {
	
	Timesheet saveTimesheet(Timesheet timesheet);

	/**
	 * Retrieves a Timesheet by its ID.
	 * 
	 * @param id the ID of the Timesheet
	 * @return the Timesheet with the specified ID
	 */

}
