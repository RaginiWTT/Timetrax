package com.wtt.TimetraxRestApis.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.TimesheetLineHour;

public interface TimesheetLineHourRepository extends JpaRepository<TimesheetLineHour, Long> {
	
	TimesheetLineHour findByLine_LineIdAndWeekDate(Integer lineId, LocalDate weekDate);
}

