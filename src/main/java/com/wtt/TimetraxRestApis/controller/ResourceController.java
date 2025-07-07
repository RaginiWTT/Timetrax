package com.wtt.TimetraxRestApis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
		System.out.println("Creating resource: " + resource);
		Resource savedResource = resourceService.createResource(resource);
		return  new ResponseEntity<>(savedResource,HttpStatus.CREATED);
	}
	
   
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Resource> updateCustomer(@PathVariable("id") int id, @RequestBody Resource resource)
	{
		resource.setResourceId(id); // Set the ID of the Resource to be updated
		Resource updatedResource = resourceService.updateResource(resource);
		return new ResponseEntity<>(updatedResource, HttpStatus.OK);
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
	public ResponseEntity<Resource> loginResourceByEmailId_Password(@RequestParam("emailId") String emailId,
			@RequestParam("password") String password) {
		Resource resource = resourceService.loginResourceByEmailId_Password(emailId, password);
		if (resource != null) {
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
}
