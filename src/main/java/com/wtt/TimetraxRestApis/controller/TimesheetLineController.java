package com.wtt.TimetraxRestApis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wtt.TimetraxRestApis.dto.TimesheetResponseLineDTO;
import com.wtt.TimetraxRestApis.service.TimesheetLineService;

import java.util.List;

@RestController
@RequestMapping("/api/timesheet-lines")
public class TimesheetLineController {

    private final TimesheetLineService lineService;

    @Autowired
    public TimesheetLineController(TimesheetLineService lineService) {
        this.lineService = lineService;
    }

    @GetMapping("/by-timesheet/{timesheetId}")
    public List<TimesheetResponseLineDTO> getLinesByTimesheetId(@PathVariable Integer timesheetId) {
        return lineService.getAllLinesByTimesheetId(timesheetId);
    }
    
    
    // 
}
