package com.wtt.TimetraxRestApis.service;

import com.wtt.TimetraxRestApis.entity.Project;

public interface ProjectService {
	
	Project createProject(Project project, Integer customerId);
	
	Project updateProject(Project project, Integer projectId, int  customerId);

}
