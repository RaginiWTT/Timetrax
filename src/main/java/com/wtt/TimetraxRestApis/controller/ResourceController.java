package com.wtt.TimetraxRestApis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

}
