package com.wtt.TimetraxRestApis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
	
    List<Timesheet> findByWeekStartDateAndStatusId(LocalDate weekStartDate, Integer statusId);

}

