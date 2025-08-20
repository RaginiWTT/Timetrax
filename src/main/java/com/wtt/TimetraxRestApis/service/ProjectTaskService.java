package com.wtt.TimetraxRestApis.service;

import java.util.List;

import com.wtt.TimetraxRestApis.dto.ProjectTaskDTO;
import com.wtt.TimetraxRestApis.dto.ProjectTaskResponseDTO;
import com.wtt.TimetraxRestApis.entity.ProjectTask;

public interface ProjectTaskService {
	ProjectTaskResponseDTO createProjectTask(ProjectTaskDTO projectTaskDTO);
    List<ProjectTaskResponseDTO> getTasksByProjectId(Integer projectId);
    
    // method for updating the task
    //	ProjectTaskResponseDTO updateProjectTask(ProjectTaskDTO projectTaskDTO, Integer taskId);	

	ProjectTaskDTO updateProjectTask(ProjectTaskDTO projectTaskDTO, int taskId);
//
	ProjectTaskDTO getProjectTaskById(Integer taskId);
//
//	List<ProjectTask> getAllProjectTasks();
//
//	void deleteProjectTaskById(Integer taskId);

}
