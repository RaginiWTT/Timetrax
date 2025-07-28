package com.wtt.TimetraxRestApis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.TimesheetLine;

public interface TimesheetLineRepository extends JpaRepository<TimesheetLine, Long> {}

