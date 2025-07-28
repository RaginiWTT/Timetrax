package com.wtt.TimetraxRestApis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.entity.Timesheet;
import com.wtt.TimetraxRestApis.entity.TimesheetLine;
import com.wtt.TimetraxRestApis.entity.TimesheetLineHour;
import com.wtt.TimetraxRestApis.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements TimesheetService{

    @Autowired
    private TimesheetRepository timesheetRepository;



	@Override
	    public Timesheet saveTimesheet(Timesheet timesheet) {
	        for (TimesheetLine line : timesheet.getTimesheetLines()) {
	            line.setTimesheet(timesheet);
	            for (TimesheetLineHour hour : line.getHours()) {
	                hour.setLine(line);
	            }
	        }
	        return timesheetRepository.save(timesheet);
	    
	}
}
