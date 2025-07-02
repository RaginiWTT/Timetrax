package com.wtt.TimetraxRestApis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;

import lombok.AllArgsConstructor;

//This class is a placeholder for the implementation of ResourceService.
@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepo resourceRepo;
	
	@Override
	public Resource createResource(Resource resource) {
		// TODO Auto-generated method stub
		return  resourceRepo.save(resource);
	}

}
