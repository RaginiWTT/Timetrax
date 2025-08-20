package com.wtt.TimetraxRestApis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wtt.TimetraxRestApis.dto.ProjectTaskDTO;
import com.wtt.TimetraxRestApis.dto.ProjectTaskResponseDTO;
import com.wtt.TimetraxRestApis.entity.ProjectTask;
import com.wtt.TimetraxRestApis.service.ProjectTaskService;

@RestController
@RequestMapping("/api/tasks")
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody ProjectTaskDTO dto) {
        try {
            ProjectTaskResponseDTO savedTask = projectTaskService.createProjectTask(dto);
            return ResponseEntity.ok(savedTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<?>> getActiveTasksByProject(@PathVariable Integer projectId) {
        List<ProjectTaskResponseDTO> tasks = projectTaskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }
    
    @PutMapping("/update/{taskId}")
	public ResponseEntity<?> updateTask(@RequestBody ProjectTaskDTO projectTaskDTO, @PathVariable int taskId) {
		try {
			ProjectTaskDTO updatedTask = projectTaskService.updateProjectTask(projectTaskDTO, taskId);
			return ResponseEntity.ok(updatedTask);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
    
	@GetMapping("/{taskId}")
	public ResponseEntity<ProjectTaskDTO> getTaskById(@PathVariable Integer taskId) {
		ProjectTaskDTO task = projectTaskService.getProjectTaskById(taskId);
		return ResponseEntity.ok(task);
	}

//	@GetMapping("/all")
//	public ResponseEntity<List<ProjectTask>> getAllTasks() {
//		List<ProjectTask> tasks = projectTaskService.getAllProjectTasks();
//		return ResponseEntity.ok(tasks);
//	}
//
//	@DeleteMapping("/delete/{taskId}")
//	public ResponseEntity<String> deleteTaskById(@PathVariable Integer taskId) {
//		projectTaskService.deleteProjectTaskById(taskId);
//		return ResponseEntity.ok("Task deleted successfully");
//	}
}

