package com.wtt.TimetraxRestApis.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wtt.TimetraxRestApis.dto.TimesheetDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetHourDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetLineDTO;
import com.wtt.TimetraxRestApis.dto.TimesheetResponseDTO;
import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.entity.Timesheet;
import com.wtt.TimetraxRestApis.entity.TimesheetLine;
import com.wtt.TimetraxRestApis.entity.TimesheetLineHour;
import com.wtt.TimetraxRestApis.repository.ProjectRepo;
import com.wtt.TimetraxRestApis.repository.ProjectTaskRepository;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;
import com.wtt.TimetraxRestApis.repository.TimesheetRepository;
import com.wtt.TimetraxRestApis.service.TimesheetService;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

	@Autowired
	TimesheetRepository timesheetRepository;

	@Autowired
	private TimesheetService timesheetService;

	@Autowired
	ResourceRepo repo;

	@Autowired
	ProjectTaskRepository taskRepository;

	@Autowired
	ProjectRepo projectRepository;

//    @PostMapping("/submit")
//    public ResponseEntity<Timesheet> submitTimesheet(@RequestBody Timesheet timesheet) {
//    	  System.out.println("Received timesheet: " + timesheet);
//        Timesheet saved = timesheetService.saveTimesheet(timesheet);
//        return ResponseEntity.ok(saved);
//    }
//
////    @GetMapping
////    public List<Timesheet> getAllTimesheets() {
////        return timesheetService.getAllTimesheets();
////    }
///
///

	@PostMapping("/submit")
	public ResponseEntity<String> saveTimesheet(@RequestBody TimesheetDTO dto) {
		Resource resource = repo.findById(dto.getResourceId())
				.orElseThrow(() -> new RuntimeException("Resource not found"));

		System.out.println("Received timesheet: " + dto);
		System.out.println("Resource ID: " + dto.getResourceId());

		Timesheet timesheet = new Timesheet();
		timesheet.setResourceId(resource);
		timesheet.setCreatedBy(dto.getCreatedBy());
		timesheet.setCreatedDateTime(dto.getCreatedDateTime());
		timesheet.setSubmittedBy(dto.getSubmittedBy());
		timesheet.setStatusId(dto.getStatusId());
		// Set other fields

		List<TimesheetLine> lines = new ArrayList<>();
		for (TimesheetLineDTO lineDTO : dto.getLines()) {
			TimesheetLine line = new TimesheetLine();

			line.setProject(projectRepository.findById(lineDTO.getProjectId())
					.orElseThrow(() -> new RuntimeException("Project Id not found")));

			line.setTask(taskRepository.findById(lineDTO.getTaskId())
					.orElseThrow(() -> new RuntimeException("Task Id not found")));

			// line.setProjectId(lineDTO.getProjectId());
			line.setStatus(lineDTO.getStatus());
			line.setCreatedBy(lineDTO.getCreatedBy());
			line.setCreatedDateTime(lineDTO.getCreatedDateTime());
			line.setModifiedBy(lineDTO.getModifiedBy());
			line.setModifiedDateTime(lineDTO.getModifiedDateTime());
			line.setTimesheet(timesheet); // Important to set back-reference

			List<TimesheetLineHour> hours = new ArrayList<>();
			for (TimesheetHourDTO hourDTO : lineDTO.getHours()) {
				TimesheetLineHour hour = new TimesheetLineHour();
				hour.setWeekDate(hourDTO.getWeekDate());
				hour.setWorkingHours_Billable(hourDTO.getWorkingHours_Billable());
				hour.setWorkingHours_NotBillable(hourDTO.getWorkingHours_NotBillable());
				hour.setNotes(hourDTO.getNotes());
				hour.setCreatedBy(hourDTO.getCreatedBy());
				hour.setCreatedDateTime(hourDTO.getCreatedDateTime());
				hour.setLine(line); // Back-reference

				hours.add(hour);
			}
			line.setHours(hours);
			lines.add(line);
		}

		timesheet.setTimesheetLines(lines);
		timesheetRepository.save(timesheet);

		return ResponseEntity.ok("Timesheet saved successfully");
	}

	@GetMapping("/getTimesheetByStartDateAndStatusId")
	public ResponseEntity<List<TimesheetResponseDTO>> getTimesheetByStartDateAndStatusId(
			@RequestParam("weekStartDate") LocalDate weekStartDateStr, @RequestParam("statusId") Integer statusId) {
		List<TimesheetResponseDTO> dtos = timesheetService.getTimehseetByStartDateAndStatusId(weekStartDateStr,
				statusId);

		return new ResponseEntity<>(dtos, dtos != null && !dtos.isEmpty() ? org.springframework.http.HttpStatus.OK
				: org.springframework.http.HttpStatus.NO_CONTENT);
		// return dtos != null && !dtos.isEmpty() ? ResponseEntity.ok(dtos) :
		// ResponseEntity.noContent().build();
	}
}
