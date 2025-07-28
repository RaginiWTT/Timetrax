package com.wtt.TimetraxRestApis.service;

import java.util.List;

import com.wtt.TimetraxRestApis.dto.ProjectTaskDTO;
import com.wtt.TimetraxRestApis.dto.ProjectTaskResponseDTO;
import com.wtt.TimetraxRestApis.entity.ProjectTask;

public interface ProjectTaskService {
	ProjectTaskResponseDTO createProjectTask(ProjectTaskDTO projectTaskDTO);
    List<ProjectTaskResponseDTO> getTasksByProjectId(Integer projectId);


//	ProjectTaskDTO updateProjectTask(ProjectTaskDTO projectTaskDTO, int taskId);
//
//	ProjectTask getProjectTaskById(Integer taskId);
//
//	List<ProjectTask> getAllProjectTasks();
//
//	void deleteProjectTaskById(Integer taskId);

}
