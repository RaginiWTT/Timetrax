package com.wtt.TimetraxRestApis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wtt.TimetraxRestApis.dto.ProjectDTO;
import com.wtt.TimetraxRestApis.entity.Project;
import com.wtt.TimetraxRestApis.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rest APIs for Project", description = "These APIs are designed for managing the projects in the Timetrax application")
@RestController
@RequestMapping("/api/project")
public class ProjectController {

	private ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	// Define your endpoints here for project-related operations
	// For example, you might have methods like createProject, getProjectById, etc.

	@Operation(summary = "Create a new project", description = "This API creates a new project in the Timetrax application.")
	@ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
	@PostMapping("/add/{customerId}")
	public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO, @PathVariable int customerId) {
		System.out.println("Creating project: " + projectDTO);
		ProjectDTO createdProjectDTO = projectService.createProject(projectDTO, customerId);
		return new ResponseEntity<>(createdProjectDTO, org.springframework.http.HttpStatus.CREATED);
	}

	@Operation(summary = "Update an existing project", description = "This API updates the information of an existing project in the Timetrax application.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@PutMapping("/modify/{projectId}")
	public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDto, @PathVariable int projectId,
			@RequestParam(required = false) Integer customerId) {

		System.out.println("Updating project: " + projectDto);
		System.out.println("Customer ID: " + customerId);
		ProjectDTO updatedProjectDTO = projectService.updateProject(projectDto, projectId, customerId);

		return new ResponseEntity<>(updatedProjectDTO, org.springframework.http.HttpStatus.OK);

	}

	@Operation(summary = "Get project by ID", description = "This API retrieves a project by its ID from the Timetrax application.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable("projectId") Integer projectId) {
		Project project = projectService.getProjectById(projectId);
		return new ResponseEntity<>(project, org.springframework.http.HttpStatus.OK);
	}

	@Operation(summary = "Get all projects by customer ID", description = "This API retrieves all projects associated with a specific customer ID from the Timetrax application.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<java.util.List<Project>> getAllProjectsByCustomerId(
			@PathVariable("customerId") Integer customerId) {
		java.util.List<Project> projects = projectService.getAllProjectsByCustomerId(customerId);
		return new ResponseEntity<>(projects, org.springframework.http.HttpStatus.OK);
	}

	@Operation(summary = "Get all projects", description = "This API retrieves all projects from the Timetrax application.")
	@ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
	@GetMapping("/all")
	public ResponseEntity<java.util.List<Project>> getAllProjects() {
		java.util.List<Project> projects = projectService.getAllProjects();
		return new ResponseEntity<>(projects, org.springframework.http.HttpStatus.OK);
	}
	
	
}
