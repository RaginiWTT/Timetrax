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
}

