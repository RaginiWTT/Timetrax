package com.wtt.TimetraxRestApis.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.dto.ProjectTaskDTO;
import com.wtt.TimetraxRestApis.dto.ProjectTaskResponseDTO;
import com.wtt.TimetraxRestApis.entity.Project;
import com.wtt.TimetraxRestApis.entity.ProjectTask;
import com.wtt.TimetraxRestApis.repository.ProjectRepo;
import com.wtt.TimetraxRestApis.repository.ProjectTaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService{

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepo projectRepository;
    
    @Autowired
    private ModelMapper mapper;


	@Override
	public ProjectTaskResponseDTO createProjectTask(ProjectTaskDTO dto) {
//	    ProjectTask task = new ProjectTask();
//        task.setTaskName(dto.getTaskName());
//        task.setActive(dto.getActive());
//        task.setCreatedBy(dto.getCreatedBy());
//        task.setCreatedDateTime(LocalDateTime.now());
		
		

        Project project = projectRepository.findById(dto.getProjectId())
                            .orElseThrow(() -> new RuntimeException("Project not found"));
        // strict model mapping
		mapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);

        
        ProjectTask task = mapper.map(dto, ProjectTask.class);

        task.setProject(project);
        
        ProjectTask savedTask =  projectTaskRepository.save(task);
        
        ProjectTaskResponseDTO taskDTO = mapper.map(savedTask, ProjectTaskResponseDTO.class);
        taskDTO.setProjectId(savedTask.getProject().getProjectId());
        taskDTO.setProjectName(savedTask.getProject().getProjectName());

        return taskDTO;
	}
	 @Override
	    public List<ProjectTaskResponseDTO> getTasksByProjectId(Integer projectId) {
	        List<ProjectTask> tasks = projectTaskRepository.findByProject_ProjectIdAndActiveTrue(projectId);
	        return tasks.stream().map(task -> {
	            ProjectTaskResponseDTO dto = mapper.map(task, ProjectTaskResponseDTO.class);
	            dto.setProjectId(task.getProject().getProjectId());
	            dto.setProjectName(task.getProject().getProjectName());
	            return dto;
	        }).collect(Collectors.toList());
	    }
	@Override
	public ProjectTaskDTO updateProjectTask(ProjectTaskDTO projectTaskDTO, int taskId) {
		// TODO Auto-generated method stub
		ProjectTask existingTask = projectTaskRepository.findById(taskId)
				.orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
		if (existingTask != null) {
			
			Project project = projectRepository.findById(projectTaskDTO.getProjectId()).orElseThrow(
					() -> new RuntimeException("Project not found with id: " + projectTaskDTO.getProjectId()));
			
	
			// Update the fields of the existing task
			existingTask.setTaskName(projectTaskDTO.getTaskName());
			existingTask.setActive(projectTaskDTO.getActive());
			existingTask.setModifiedBy(projectTaskDTO.getModifiedBy());
			existingTask.setProject(project);
			
			
		
			
			//existingTask.setModifiedDateTime(LocalDateTime.now());

			// Save the updated task
			ProjectTask updatedTask = projectTaskRepository.save(existingTask);

			// Map to DTO and return
			return mapper.map(updatedTask, ProjectTaskDTO.class);
		}
		else
		{
			throw new RuntimeException("Task not found with id: " + taskId);
		}
		
		}
	@Override
	public ProjectTaskDTO getProjectTaskById(Integer taskId) {
		 // Retrieve the task by ID
			ProjectTaskDTO taskDTO = projectTaskRepository.findById(taskId)
					.map(task -> mapper.map(task, ProjectTaskDTO.class))
					.orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
		return taskDTO;
        }
	
}

