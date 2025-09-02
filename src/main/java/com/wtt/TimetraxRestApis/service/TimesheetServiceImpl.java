package com.wtt.TimetraxRestApis.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wtt.TimetraxRestApis.dto.TimesheetApprovalResponseDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetHourDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetLineDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetReportDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetResponseDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetResponseLineDTO;
import com.wtt.TimetraxRestApis.entity.Timesheet;
import com.wtt.TimetraxRestApis.entity.TimesheetLine;
import com.wtt.TimetraxRestApis.entity.TimesheetLineHour;
import com.wtt.TimetraxRestApis.repository.ProjectTaskRepository;
import com.wtt.TimetraxRestApis.repository.ProjectTaskStatusRepository;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;
import com.wtt.TimetraxRestApis.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements TimesheetService{

    @Autowired
    private TimesheetRepository timesheetRepository;
    
    @Autowired
    private ResourceRepo resourceRepo;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    
    @Autowired
    ProjectTaskStatusRepository projectTaskStatusRepository;


	@Override
	    public Timesheet saveTimesheet(Timesheet timesheet) {
		System.out.println("Inside saveTimesheet method of TimesheetServiceImpl "+timesheet.getWeekEndDate());
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
	
//	
//    @Override
//    @Transactional
//    public void approveTimesheet(Integer timesheetId, Integer statusId, Integer approvedBy, Integer active) {
//    timesheetRepository.updateTimesheetStatus(timesheetId, statusId, approvedBy);
//      projectTaskRepository.updateActiveFlagForTasks(timesheetId, active);
//    }
//	
	
	@Override
	@Transactional
	public TimesheetApprovalResponseDTO approveTimesheet(Integer timesheetId, Integer statusId, Integer approvedBy, Integer activeFlag) {
	   
		Long longValue = timesheetId.longValue();

		Optional<Timesheet> optionalTimesheet = timesheetRepository.findById(longValue);

	    if (optionalTimesheet.isEmpty()) {
	        return new TimesheetApprovalResponseDTO(timesheetId, "Timesheet not found", false);
	    }

	    Timesheet timesheet = optionalTimesheet.get();

	    // ✅ Business Rule: don't approve if already approved
	    if (timesheet.getStatusId() != null && timesheet.getStatusId() == 2) {
	        return new TimesheetApprovalResponseDTO(timesheetId, "Timesheet already approved", false);
	    }

	    // ✅ Perform updates
	    timesheetRepository.updateTimesheetStatus(timesheetId, statusId, approvedBy);
	    projectTaskRepository.updateActiveFlagForTasks(timesheetId, activeFlag);

	    return new TimesheetApprovalResponseDTO(timesheetId, "Timesheet approved successfully", true);
	}

	@Override
	public Boolean checkExistsByResourceIdAndWeekStartAndWeekEndDate(Integer resourceId, LocalDate weekStartDate,
			LocalDate weekEndDate) {
		Boolean exists = timesheetRepository.existsByResourceId_ResourceIdAndWeekStartDateAndWeekEndDate(resourceId, weekStartDate, weekEndDate);
		if (exists) {
			return true;
		} else {
			return false;
		}
		//return ;
	}

//	@Override
//	public List<TimesheetReportDTO> getTimesheetByResourceIdAndStatusId(Integer resourceId, Integer statusId) {
//
//		   List<TimesheetReportDTO> reportDTOs = new ArrayList<>();
//       List<Timesheet> timesheet = timesheetRepository.findByResourceId_ResourceIdAndStatusId(resourceId, statusId);
//		if (timesheet != null) {
//                     
//                                    for (Timesheet ts : timesheet) {
//			TimesheetReportDTO reportDTO = new TimesheetReportDTO();
//			reportDTO.setTimesheetId(ts.getTimesheetId());
//			reportDTO.setResourceId(ts.getResourceId().getResourceId());
//			reportDTO.setResourceName(
//					ts.getResourceId().getFirstName() + " " + ts.getResourceId().getLastName());
//			reportDTO.setStatusId(ts.getStatusId());
//			reportDTO.setStatusName(projectTaskStatusRepository.findById(ts.getStatusId())
//					.map(status -> status.getStatusName()).orElse("Unknown Status"));
//			reportDTO.setWeekStartDate(ts.getWeekStartDate().toString());
//			reportDTO.setWeekEndDate(ts.getWeekEndDate().toString());
//			reportDTO.setTotalHours(ts.getTotalHours());
//			reportDTO.setStatusId(ts.getStatusId());
//			
//			
//			
//
//			// Map TimesheetLines to DTOs
//			List<TimesheetResponseLineDTO> timesheetResponseLineDTOs = new ArrayList<>();
//			for (TimesheetLine line : ts.getTimesheetLines()) {
//				TimesheetResponseLineDTO lineDTO = new TimesheetResponseLineDTO();
//                lineDTO.setLineId(line.getLineId());
//                lineDTO.setProjectId(line.getProject().getProjectId());
//                lineDTO.setProjectName(line.getProject().getProjectName());
//                lineDTO.setTaskId(line.getTask().getTaskId());
//                lineDTO.setTaskName(line.getTask().getTaskName());
//                // Map Hours to DTOs
////                List<TimesheetLineHour> hourDTOs = new ArrayList<>();
////                for (TimesheetLineHour hour : line.getHours()) {
////                    TimesheetLineHour hourDTO = new TimesheetLineHour();
////                    hourDTO.setHourId(hour.getHourId());
////                    hourDTO.setDayOfWeek(hour.getDayOfWeek());
////                    hourDTO.setHoursWorked(hour.getHoursWorked());
////                    hourDTOs.add(hourDTO);
////                }
////                lineDTO.setHours(hourDTOs);
//                
//                timesheetResponseLineDTOs.add(lineDTO);
//			
//
//				
//			}
//			
//			reportDTO.setTimeshhetLineDTO(timesheetResponseLineDTOs);
//
//           }
//                                    
//                                    System.out.println("Report DTOs: " + reportDTOs); // Debugging line
//		
//		return reportDTOs;
//		
//		
//	}
//
//	    	
//	    return reportDTOs;
//}
	
	@Override
	public List<TimesheetReportDTO> getTimesheetByResourceIdAndStatusId(Integer resourceId, Integer statusId) {
	    List<TimesheetReportDTO> reportDTOs = new ArrayList<>();

	    // ✅ Fix repository method name
	    List<Timesheet> timesheets = timesheetRepository.findByResourceId_ResourceIdAndStatusId(resourceId, statusId);

	    if (timesheets != null && !timesheets.isEmpty()) {
	        for (Timesheet ts : timesheets) {
	            TimesheetReportDTO reportDTO = new TimesheetReportDTO();
	            reportDTO.setTimesheetId(ts.getTimesheetId());
	            reportDTO.setResourceId(ts.getResourceId().getResourceId());
	            reportDTO.setResourceName(ts.getResourceId().getFirstName() + " " + ts.getResourceId().getLastName());
	            reportDTO.setStatusId(ts.getStatusId());
	            reportDTO.setStatusName(projectTaskStatusRepository.findById(ts.getStatusId())
	                    .map(status -> status.getStatusName()).orElse("Unknown Status"));
	            reportDTO.setWeekStartDate(ts.getWeekStartDate().toString());
	            reportDTO.setWeekEndDate(ts.getWeekEndDate().toString());
	            reportDTO.setTotalHours(ts.getTotalHours());

	            // Map TimesheetLines to DTOs
	            List<TimesheetResponseLineDTO> timesheetResponseLineDTOs = new ArrayList<>();
	            for (TimesheetLine line : ts.getTimesheetLines()) {
	                TimesheetResponseLineDTO lineDTO = new TimesheetResponseLineDTO();
	                lineDTO.setLineId(line.getLineId());
	                lineDTO.setProjectId(line.getProject().getProjectId());
	                lineDTO.setProjectName(line.getProject().getProjectName());
	                lineDTO.setTaskId(line.getTask().getTaskId());
	                lineDTO.setTaskName(line.getTask().getTaskName());
	                timesheetResponseLineDTOs.add(lineDTO);
	            }

	            reportDTO.setTimeshhetLineDTO(timesheetResponseLineDTOs);

	            // ✅ Add to list
	            reportDTOs.add(reportDTO);
	        }
	    }

	    System.out.println("Report DTOs: " + reportDTOs); // Debugging
	    return reportDTOs;
	}
	
	// find all timesheets by resourceId
	@Override
	public List<TimesheetReportDTO> getTimesheetByResourceId(Integer resourceId) {
		List<TimesheetReportDTO> reportDTOs = new ArrayList<>();

		// ✅ Fix repository method name
		List<Timesheet> timesheets = timesheetRepository.findByResourceId_ResourceId(resourceId);

		if (timesheets != null && !timesheets.isEmpty()) {
			for (Timesheet ts : timesheets) {
				TimesheetReportDTO reportDTO = new TimesheetReportDTO();
				reportDTO.setTimesheetId(ts.getTimesheetId());
				reportDTO.setResourceId(ts.getResourceId().getResourceId());
				reportDTO.setResourceName(ts.getResourceId().getFirstName() + " " + ts.getResourceId().getLastName());
				reportDTO.setStatusId(ts.getStatusId());
				reportDTO.setStatusName(projectTaskStatusRepository.findById(ts.getStatusId())
						.map(status -> status.getStatusName()).orElse("Unknown Status"));
				reportDTO.setWeekStartDate(ts.getWeekStartDate().toString());
				reportDTO.setWeekEndDate(ts.getWeekEndDate().toString());
				reportDTO.setTotalHours(ts.getTotalHours());

//				// Map TimesheetLines to DTOs
//				List<TimesheetResponseLineDTO> timesheetResponseLineDTOs = new ArrayList<>();
//				for (TimesheetLine line : ts.getTimesheetLines()) {
//					TimesheetResponseLineDTO lineDTO = new TimesheetResponseLineDTO();
//					lineDTO.setLineId(line.getLineId());
//					lineDTO.setProjectId(line.getProject().getProjectId());
//					lineDTO.setProjectName(line.getProject().getProjectName());
//					lineDTO.setTaskId(line.getTask().getTaskId());
//					lineDTO.setTaskName(line.getTask().getTaskName());
//					timesheetResponseLineDTOs.add(lineDTO);
//				}
//
//				reportDTO.setTimeshhetLineDTO(timesheetResponseLineDTOs);

				// ✅ Add to list
				reportDTOs.add(reportDTO);
			}
		}

		System.out.println("Report DTOs: " + reportDTOs); // Debugging
		return reportDTOs;
	}
	
	// find complete details by its timesheetId
	@Override
	public TimesheetDTO findByTimesheetId(Integer timesheetId) {
	Timesheet timesheet= timesheetRepository.findByTimesheetId(timesheetId);
	
	if(timesheet != null) {
		
		TimesheetDTO dto = new TimesheetDTO();
		dto.setTimesheetId(timesheet.getTimesheetId());
		dto.setResourceId(timesheet.getResourceId().getResourceId());
		dto.setWeekStartDate(timesheet.getWeekStartDate());
		dto.setWeekEndDate(timesheet.getWeekEndDate());
		dto.setCreatedBy(timesheet.getCreatedBy());
		dto.setCreatedDateTime(timesheet.getCreatedDateTime());
		//dto.setSubmittedBy(timesheet.getSubmittedBy());
		dto.setTotalHours(timesheet.getTotalHours());
		
		dto.setStatusId(timesheet.getStatusId());
		// Map TimesheetLines to DTOs
		List<TimesheetLineDTO> timesheetLineDTOs = new ArrayList<>();
		for (TimesheetLine line : timesheet.getTimesheetLines()) {
			TimesheetLineDTO lineDTO = new TimesheetLineDTO();
			lineDTO.setLineId(line.getLineId());
			lineDTO.setProjectId(line.getProject().getProjectId());
			lineDTO.setTaskId(line.getTask().getTaskId());
			lineDTO.setProjectName(line.getProject().getProjectName());
			lineDTO.setTaskName(line.getTask().getTaskName());

			
			// Map Hours to DTOs
			List<TimesheetHourDTO> hourDTOs = new ArrayList<>();
			for (TimesheetLineHour hour : line.getHours()) {
				TimesheetHourDTO hourDTO = new TimesheetHourDTO();
				hourDTO.setHourId(hour.getHourId());
				hourDTO.setWeekDate(hour.getWeekDate());
				hourDTO.setWorkingHours_Billable(hour.getWorkingHours_Billable());
				hourDTO.setWorkingHours_NotBillable(hour.getWorkingHours_NotBillable());
				hourDTO.setNotes(hour.getNotes());
				hourDTOs.add(hourDTO);
			}
			lineDTO.setHours(hourDTOs);

			timesheetLineDTOs.add(lineDTO);
		}
		dto.setLines(timesheetLineDTOs);
		return dto;
		
	}
		
		return null;
	}

	
}
