package com.wtt.TimetraxRestApis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.entity.Project;
import com.wtt.TimetraxRestApis.repository.ProjectRepo;

@Service
public class ProjectServiceImpl implements ProjectService {

	// Assuming you have a ProjectRepo and CustomerService injected here
	@Autowired
	private ProjectRepo projectRepo;
	@Autowired
	private CustomerService customerService;
	// Constructor injection can also be used instead of field injection

//	 public ProjectServiceImpl(ProjectRepo projectRepo, CustomerService
//	 customerService) {
//	 this.projectRepo = projectRepo;
//	 this.customerService = customerService;
//	 }

	@Override
	public Project createProject(Project project, Integer customerId) {
		// Logic to create a project
		// For example, you might want to fetch the customer by ID and set it in the
		// project
		Customer customer = customerService.getCustomerById(customerId);
		project.setCustomer(customer);

		Project createdProject = projectRepo.save(project);
		return createdProject; // Placeholder for actual implementation
	}

	@Override
	public Project updateProject(Project project, Integer projectId,int customerId) {
		// TODO Auto-generated method stub
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
				
				return updaProject; // Return the updated project

			}
		}
		return null;
	}

}
