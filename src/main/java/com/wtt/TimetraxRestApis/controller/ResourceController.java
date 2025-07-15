package com.wtt.TimetraxRestApis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wtt.TimetraxRestApis.dto.ResourceDTO;
import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.service.ResourceService;


@RestController
@RequestMapping("/api/resource")
public class ResourceController {
	
	
	private ResourceService resourceService;
	
	  @Autowired
	    public ResourceController(ResourceService resourceService) {
	        this.resourceService = resourceService;
	    }
	
	//handler method for creating a resource 
	@PostMapping("/add")
	public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) {
		System.out.println("Creating resource: " + resourceDTO);
		ResourceDTO savedResource = resourceService.createResource(resourceDTO);
		return  new ResponseEntity<>(savedResource,HttpStatus.CREATED);
	}
	
   
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResourceDTO> updateCustomer(@PathVariable("id") int id, @RequestBody ResourceDTO resourceDTO)
	{
		//resourceDTO.setResourceId(id); // Set the ID of the Resource to be updated
		ResourceDTO updatedResourceDTO = resourceService.updateResource(resourceDTO,id);
		return new ResponseEntity<>(updatedResourceDTO, HttpStatus.OK);
	}
	
//	@PostMapping("/login/{emailId}/{password}")
//	public ResponseEntity<Resource> loginResourceByEmailId_Password(@PathVariable("emailId") String emailId,
//			@PathVariable("password") String password) {
//		Resource resource = resourceService.loginResourceByEmailId_Password(emailId, password);
//		if (resource != null) {
//			return new ResponseEntity<>(resource, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//	}
	
	@PostMapping("/login")
	public ResponseEntity<ResourceDTO> loginResourceByEmailId_Password(@RequestParam("emailId") String emailId,
			@RequestParam("password") String password) {
		ResourceDTO resourceDTO = resourceService.loginResourceByEmailId_Password(emailId, password);
		if (resourceDTO != null) {
			return new ResponseEntity<>(resourceDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	// Get Resource By Id method
	@GetMapping("/{resourceId}")
	public ResponseEntity<Resource> getResourceById(@PathVariable("resourceId") Integer resourceId) {
		Resource resource = resourceService.getResourceById(resourceId);
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Resource>> getAllResources() {
		List<Resource> resources = resourceService.getAllResources();
		if (resources != null && !resources.isEmpty()) {
			return new ResponseEntity<>(resources, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
}
