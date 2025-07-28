package com.wtt.TimetraxRestApis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {}

