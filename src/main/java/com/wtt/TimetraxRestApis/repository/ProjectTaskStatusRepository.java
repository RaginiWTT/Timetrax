package com.wtt.TimetraxRestApis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.ProjectTaskStatus;

public interface ProjectTaskStatusRepository extends JpaRepository<ProjectTaskStatus, Integer> {
    
    // Custom query methods can be defined here if needed
	
}