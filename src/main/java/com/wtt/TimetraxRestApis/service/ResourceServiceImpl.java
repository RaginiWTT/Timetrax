package com.wtt.TimetraxRestApis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.entity.Customer;
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
		return resourceRepo.save(resource);
	}

	@Override
	public Resource updateResource(Resource resource) {
		Resource existingResource = resourceRepo.findById(resource.getResourceId()).get();
		if (existingResource != null) {
			existingResource.setEmailId(resource.getEmailId());
			existingResource.setFirstName(resource.getFirstName());
			existingResource.setLastName(resource.getLastName());
			existingResource.setPhoneNumber(resource.getPhoneNumber());
			existingResource.setAddressLine1(resource.getAddressLine1());
			existingResource.setAddressLine2(resource.getAddressLine2());
			existingResource.setCity(resource.getCity());
			existingResource.setState(resource.getState());
			existingResource.setZipcode(resource.getZipcode());
			existingResource.setCountry(resource.getCountry());
			existingResource.setActive(resource.getActive());
			existingResource.setPassword(resource.getPassword());
			existingResource.setRole(resource.getRole());
			existingResource.setModifiedBy(resource.getModifiedBy());
			Resource updatedResource = resourceRepo.save(existingResource);

			return updatedResource;
		}
		return null;

	}
}
