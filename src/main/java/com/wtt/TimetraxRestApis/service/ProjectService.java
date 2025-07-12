package com.wtt.TimetraxRestApis.service;

import com.wtt.TimetraxRestApis.dto.ProjectDTO;
import com.wtt.TimetraxRestApis.entity.Project;

public interface ProjectService {
	
	ProjectDTO createProject(ProjectDTO projectDTO, Integer customerId);
	
	ProjectDTO updateProject(ProjectDTO projectDt, Integer projectId, int  customerId);

}
