package com.wtt.TimetraxRestApis.service;

import java.util.List;

import com.wtt.TimetraxRestApis.dto.ProjectDTO;
import com.wtt.TimetraxRestApis.entity.Project;

public interface ProjectService {

	ProjectDTO createProject(ProjectDTO projectDTO, Integer customerId);

	ProjectDTO updateProject(ProjectDTO projectDt, Integer projectId, int customerId);

	Project getProjectById(Integer projectId);

//
	List<Project> getAllProjectsByCustomerId(Integer customerId);

//	
	List<Project> getAllProjects();

}
