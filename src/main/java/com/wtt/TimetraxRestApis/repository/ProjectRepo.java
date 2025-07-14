package com.wtt.TimetraxRestApis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
	// Custom query methods can be added here if needed
	// For example, to find a project by name:
	// Optional<Project> findByProjectName(String projectName);
	
	// Example of a custom query method to find a project by name
	//Optional<Project> findByProjectName(String projectName);
	Project findByProjectName(String projectName);
	

}
