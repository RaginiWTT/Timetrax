package com.wtt.TimetraxRestApis.service;

import java.util.List;

import com.wtt.TimetraxRestApis.dto.TimesheetResponseLineDTO;

public interface TimesheetLineService {
    List<TimesheetResponseLineDTO> getAllLinesByTimesheetId(Integer timesheetId);


}
