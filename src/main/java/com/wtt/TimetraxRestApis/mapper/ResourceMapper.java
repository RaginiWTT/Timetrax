package com.wtt.TimetraxRestApis.mapper;

import com.wtt.TimetraxRestApis.dto.ResourceDTO;
import com.wtt.TimetraxRestApis.entity.Resource;

public class ResourceMapper {
	// This class is used to map between Resource and ResourceDTO objects
	
	//tHIS method maps a Resource object to a ResourceDTO object
	public static ResourceDTO mapToResourceDTO(Resource resource) {
		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setEmailId(resource.getEmailId());
		resourceDTO.setFirstName(resource.getFirstName());
		resourceDTO.setLastName(resource.getLastName());
		resourceDTO.setPhoneNumber(resource.getPhoneNumber());
		resourceDTO.setAddressLine1(resource.getAddressLine1());
		resourceDTO.setAddressLine2(resource.getAddressLine2());
		resourceDTO.setCity(resource.getCity());
		resourceDTO.setState(resource.getState());
		resourceDTO.setZipcode(resource.getZipcode());
		resourceDTO.setCountry(resource.getCountry());
		resourceDTO.setRole(resource.getRole());
		resourceDTO.setActive(resource.getActive());
		resourceDTO.setCreatedBy(resource.getCreatedBy());
	
		return resourceDTO;

	}
	
	
	// This method maps a ResourceDTO object to a Resource object
	public static Resource mapToResource(ResourceDTO resourceDTO) {
		Resource resource = new Resource();
		resource.setEmailId(resourceDTO.getEmailId());
		resource.setPassword(resourceDTO.getPassword());
		resource.setFirstName(resourceDTO.getFirstName());
		resource.setLastName(resourceDTO.getLastName());
		resource.setPhoneNumber(resourceDTO.getPhoneNumber());
		resource.setAddressLine1(resourceDTO.getAddressLine1());
		resource.setAddressLine2(resourceDTO.getAddressLine2());
		resource.setCity(resourceDTO.getCity());
		resource.setState(resourceDTO.getState());
		resource.setZipcode(resourceDTO.getZipcode());
		resource.setCountry(resourceDTO.getCountry());
		resource.setRole(resourceDTO.getRole());
		resource.setActive(resourceDTO.getActive());
		resource.setCreatedBy(resourceDTO.getCreatedBy());
		resource.setModifiedBy(resourceDTO.getModifiedBy());

		return resource;
	}
	


}
