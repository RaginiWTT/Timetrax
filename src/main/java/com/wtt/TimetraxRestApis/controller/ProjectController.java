package com.wtt.TimetraxRestApis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wtt.TimetraxRestApis.entity.Project;
import com.wtt.TimetraxRestApis.service.ProjectService;

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


	 @PostMapping("/add/{customerId}")
	 public ResponseEntity<Project> createProject(@RequestBody Project project, @PathVariable int customerId) {
         System.out.println("Creating project: " + project);
          Project createdProject = projectService.createProject(project, customerId);       	 
	 return new ResponseEntity<>(createdProject, org.springframework.http.HttpStatus.CREATED);
	 }
	 
	 @PutMapping("/modify/{projectId}")
		public ResponseEntity<Project> updateProject(@RequestBody Project project, @PathVariable int projectId,
				@RequestParam(required = false) Integer customerId) {
			
         System.out.println("Updating project: " + project);
         System.out.println("Customer ID: " + customerId);
          Project updatedProject = projectService.updateProject(project, projectId, customerId);
          
          return new ResponseEntity<>(updatedProject, org.springframework.http.HttpStatus.OK);


}
}
