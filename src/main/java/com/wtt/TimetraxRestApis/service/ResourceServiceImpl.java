package com.wtt.TimetraxRestApis.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.dto.ResourceDTO;
import com.wtt.TimetraxRestApis.entity.Customer;
import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.mapper.ResourceMapper;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;

import lombok.AllArgsConstructor;

//This class is a placeholder for the implementation of ResourceService.
@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepo resourceRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResourceDTO createResource(ResourceDTO resourceDTO) {
		// Convert ResourceDTO to Resource entity

		Resource resource = ResourceMapper.mapToResource(resourceDTO);
		//Resource resource = modelMapper.map(resourceDTO, Resource.class);

		Resource savedResource = resourceRepo.save(resource);

		if (savedResource != null) {
			// Convert saved Resource entity back to ResourceDTO
			ResourceDTO savedResourceDTO = ResourceMapper.mapToResourceDTO(savedResource);
			//ResourceDTO savedResourceDTO = modelMapper.map(savedResource, ResourceDTO.class);

//		savedResourceDTO.setEmailId(savedResource.getEmailId());
//		savedResourceDTO.setFirstName(savedResource.getFirstName());
//		savedResourceDTO.setLastName(savedResource.getLastName());
//		savedResourceDTO.setPhoneNumber(savedResource.getPhoneNumber());
//		savedResourceDTO.setAddressLine1(savedResource.getAddressLine1());
//		savedResourceDTO.setAddressLine2(savedResource.getAddressLine2());
//		savedResourceDTO.setCity(savedResource.getCity());
//		savedResourceDTO.setState(savedResource.getState());
//		savedResourceDTO.setZipcode(savedResource.getZipcode());
//		savedResourceDTO.setCountry(savedResource.getCountry());
//		savedResourceDTO.setRole(savedResource.getRole());
//		savedResourceDTO.setActive(savedResource.getActive());
//		savedResourceDTO.setCreatedBy(savedResource.getCreatedBy());

			return savedResourceDTO;
		}

		return null;
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

	@Override
	public Resource loginResourceByEmailId_Password(String emailId, String password) {
		// TODO Auto-generated method stub
		Resource resource = resourceRepo.findByEmailId(emailId);
		if (resource != null) {
			// Check if the password matches
			if (resource.getPassword().equals(password)) {
				// If the password matches, return the resource
				return resource;
			}
			return null; // If the password does not match, return null;
		}
		// If no resource is found with the given emailId, return null
		return null;
	}

}
