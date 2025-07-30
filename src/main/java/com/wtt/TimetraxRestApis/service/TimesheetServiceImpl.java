package com.wtt.TimetraxRestApis.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.dto.TimesheetResponseDTO;
import com.wtt.TimetraxRestApis.entity.Timesheet;
import com.wtt.TimetraxRestApis.entity.TimesheetLine;
import com.wtt.TimetraxRestApis.entity.TimesheetLineHour;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;
import com.wtt.TimetraxRestApis.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements TimesheetService{

    @Autowired
    private TimesheetRepository timesheetRepository;
    
    @Autowired
    private ResourceRepo resourceRepo;



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
	
	@Override
	public List<TimesheetResponseDTO> getTimehseetByStartDateAndStatusId(LocalDate weekStartDate, Integer statusId) {
		// Implementation to retrieve Timesheet by week start date and status ID
		// This method should convert the retrieved Timesheet to TimesheetResponseDTO
		
		List<Timesheet> timesheets = timesheetRepository.findByWeekStartDateAndStatusId(weekStartDate, statusId);
		
		List<TimesheetResponseDTO> responseDTOs = new ArrayList<>();
		
		if (timesheets.isEmpty()) {
			return null; // or throw an exception if no timesheet found
		}
		else {
			
			for (Timesheet timesheet : timesheets) {
				TimesheetResponseDTO responseDTO = new TimesheetResponseDTO();
				responseDTO.setTimesheetId(timesheet.getTimesheetId());
				responseDTO.setResourceId(timesheet.getResourceId().getResourceId());
				
				responseDTO.setWeekStartDate(timesheet.getWeekStartDate());
				responseDTO.setWeekEndDate(timesheet.getWeekEndDate());
				responseDTO.setApprovedBy(timesheet.getApprovedBy());
				responseDTO.setSubmittedBy(timesheet.getSubmittedBy());
				responseDTO.setSubmittedDateTime(timesheet.getSubmittedDateTime());
				responseDTO.setApprovedDateTime(timesheet.getApprovedDateTime());
				responseDTO.setTotalHours(timesheet.getTotalHours());
				responseDTO.setStatusId(timesheet.getStatusId());
				responseDTO.setResourceName(
						timesheet.getResourceId().getFirstName() + " " + timesheet.getResourceId().getLastName());

				// Add logic to convert TimesheetLines and Hours to DTOs if needed
				// responseDTO.setTimesheetLines(...);
                responseDTOs.add(responseDTO);
				return responseDTOs; // Return the first found timesheet response
			}
		}
		
		return null; // Placeholder return statement
	}
}
