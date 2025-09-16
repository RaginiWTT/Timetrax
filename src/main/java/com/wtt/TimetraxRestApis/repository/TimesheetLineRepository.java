package com.wtt.TimetraxRestApis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.TimesheetLine;

public interface TimesheetLineRepository extends JpaRepository<TimesheetLine, Long> {
    List<TimesheetLine> findByTimesheet_TimesheetId(Integer timesheetId);
    
   TimesheetLine findByTimesheet_TimesheetIdAndProject_ProjectIdAndTask_TaskId(
            Integer timesheetId, Integer projectId, Integer taskId);



}

