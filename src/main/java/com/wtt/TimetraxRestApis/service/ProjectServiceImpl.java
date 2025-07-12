package com.wtt.TimetraxRestApis.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.dto.ProjectDTO;
import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.entity.Project;
import com.wtt.TimetraxRestApis.exception.ProjectAlreadyException;
import com.wtt.TimetraxRestApis.repository.ProjectRepo;

@Service
public class ProjectServiceImpl implements ProjectService {

	// Assuming you have a ProjectRepo and CustomerService injected here
	@Autowired
	private ProjectRepo projectRepo;
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ModelMapper modelMapper;
	// Constructor injection can also be used instead of field injection

//	 public ProjectServiceImpl(ProjectRepo projectRepo, CustomerService
//	 customerService) {
//	 this.projectRepo = projectRepo;
//	 this.customerService = customerService;
//	 }

	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO, Integer customerId) {
		// Logic to create a project
		// For example, you might want to fetch the customer by ID and set it in the
		// project

		Project project = modelMapper.map(projectDTO, Project.class);
		// this matching strategy will force ModelMapper to map only exact matches,
		// avoiding type confusion
		modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
		
		// Check if a project with the same name already exists
		if (projectRepo.findByProjectName(project.getProjectName()).isPresent()) {
			// If a project with the same name already exists, return null or throw an exception
			throw new ProjectAlreadyException("Project already exists with name: " + project.getProjectName());
		}

		Customer customer = customerService.getCustomerById(customerId);
		project.setCustomer(customer);

		Project createdProject = projectRepo.save(project);

		ProjectDTO createdProjectDTO = modelMapper.map(createdProject, ProjectDTO.class);
		createdProjectDTO.setCustomerId(createdProject.getCustomer().getCustomerId()); // Set the customer ID in the DTO
																						// if needed
		return createdProjectDTO; // Placeholder for actual implementation
	}

	@Override
	public ProjectDTO updateProject(ProjectDTO project, Integer projectId, int customerId) {
		// TODO Auto-generated method stub
		// Project project = modelMapper.map(projectDTO, Project.class);
		Project existingProject = projectRepo.findById(projectId).get();
		if (existingProject != null) {

			if (existingProject != null) {
				existingProject.setProjectName(project.getProjectName());
				existingProject.setProjectDescription(project.getProjectDescription());
				existingProject.setActive(project.getActive());
				existingProject.setModifiedBy(project.getModifiedBy());
				Customer customer = customerService.getCustomerById(customerId);
				System.out.println("customer: " + customer);
				if (customer != null) {
					// Handle case where customer is not found
					existingProject.setCustomer(customer);
				}

				Project updaProject = projectRepo.save(existingProject);

				ProjectDTO updaProjectDto = modelMapper.map(updaProject, ProjectDTO.class);
				updaProjectDto.setCustomerId(updaProject.getCustomer().getCustomerId()); // Set the customer ID in the
																							// DTO if needed

				return updaProjectDto; // Return the updated project

			}
		}
		return null;
	}

}
