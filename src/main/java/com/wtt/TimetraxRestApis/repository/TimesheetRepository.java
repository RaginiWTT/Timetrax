package com.wtt.TimetraxRestApis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wtt.TimetraxRestApis.entity.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
	
    List<Timesheet> findByWeekStartDateAndStatusId(LocalDate weekStartDate, Integer statusId);
    
    
    @Modifying
    @Query("UPDATE Timesheet t SET t.statusId = :statusId, t.approvedBy = :approvedBy WHERE t.timesheetId = :timesheetId")
    int updateTimesheetStatus(@Param("timesheetId") Integer timesheetId,
                              @Param("statusId") Integer statusId,
                              @Param("approvedBy") Integer approvedBy);
    
    Boolean existsByResourceId_ResourceIdAndWeekStartDateAndWeekEndDate(
            Integer resourceId, LocalDate weekStartDate, LocalDate weekEndDate
        );

}

