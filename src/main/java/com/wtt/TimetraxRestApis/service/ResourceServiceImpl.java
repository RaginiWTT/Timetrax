//package com.wtt.TimetraxRestApis.service;
//
//import java.util.List;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.modelmapper.spi.MatchingStrategy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.wtt.TimetraxRestApis.dto.ResourceDTO;
//import com.wtt.TimetraxRestApis.entity.Customer;
//import com.wtt.TimetraxRestApis.entity.Resource;
//import com.wtt.TimetraxRestApis.exception.EmailAlreadyExistException;
//import com.wtt.TimetraxRestApis.exception.ResourceNotFound;
//import com.wtt.TimetraxRestApis.mapper.ResourceMapper;
//import com.wtt.TimetraxRestApis.repository.ResourceRepo;
//
//import lombok.AllArgsConstructor;
//
////This class is a placeholder for the implementation of ResourceService.
//@Service
//@AllArgsConstructor
//public class ResourceServiceImpl implements ResourceService {
//
//	@Autowired
//	private ResourceRepo resourceRepo;
//
//	@Autowired
//	private ModelMapper modelMapper;
//
//	@Override
//	public ResourceDTO createResource(ResourceDTO resourceDTO) {
//		// this matching strategy will force ModelMapper to map only exact matches,
//		// avoiding type confusion
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//		// Convert ResourceDTO to Resource entity
//		// Resource resource = ResourceMapper.mapToResource(resourceDTO);
//		Resource resource = modelMapper.map(resourceDTO, Resource.class);
//		
//		if (resourceRepo.findByEmailId(resource.getEmailId()) != null) {
//			// If a resource with the same emailId already exists, return null or throw an exception
//		   throw new EmailAlreadyExistException("Resource already exists with given Email : " +
//							 resource.getEmailId());
//		}
//
//		Resource savedResource = resourceRepo.save(resource);
//
//		if (savedResource != null) {
//			// Convert saved Resource entity back to ResourceDTO
//			// ResourceDTO savedResourceDTO =
//			// ResourceMapper.mapToResourceDTO(savedResource);
//			ResourceDTO savedResourceDTO = modelMapper.map(savedResource, ResourceDTO.class);
//
////		savedResourceDTO.setEmailId(savedResource.getEmailId());
////		savedResourceDTO.setFirstName(savedResource.getFirstName());
////		savedResourceDTO.setLastName(savedResource.getLastName());
////		savedResourceDTO.setPhoneNumber(savedResource.getPhoneNumber());
////		savedResourceDTO.setAddressLine1(savedResource.getAddressLine1());
////		savedResourceDTO.setAddressLine2(savedResource.getAddressLine2());
////		savedResourceDTO.setCity(savedResource.getCity());
////		savedResourceDTO.setState(savedResource.getState());
////		savedResourceDTO.setZipcode(savedResource.getZipcode());
////		savedResourceDTO.setCountry(savedResource.getCountry());
////		savedResourceDTO.setRole(savedResource.getRole());
////		savedResourceDTO.setActive(savedResource.getActive());
////		savedResourceDTO.setCreatedBy(savedResource.getCreatedBy());
//
//			return savedResourceDTO;
//		}
//
//		return null;
//	}
//
//	@Override
//	public ResourceDTO updateResource(ResourceDTO resourceDTO, int resourceId) {
//		// Convert ResourceDTO to Resource entity
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		Resource resource = modelMapper.map(resourceDTO, Resource.class);
//		resource.setResourceId(resourceId); // Set the ID of the Resource to be updated
//		
//		// check if another resource with the same emailId already exists
////		Resource existingResourceWithEmail = resourceRepo.findByEmailId(resource.getEmailId());
////		if (existingResourceWithEmail != null && existingResourceWithEmail.getResourceId() != resourceId) {
////			// If a resource with the same emailId exists and it's not the one being
////			// updated, throw an exception
////			throw new EmailAlreadyExistException("Another Resource exists with given Email : " + resource.getEmailId());
////		}
//		// Fetch the existing resource from the database
//		
//		Resource existingResource = resourceRepo.findById(resource.getResourceId()).orElseThrow(
//				()-> new ResourceNotFound("Resource", "resourceId", resourceId)
//				);
//		
//		if (existingResource != null) {
//			//// check if another resource with the same emailId already exists
//			Resource existingResourceWithEmail = resourceRepo.findByEmailId(resource.getEmailId());
//			if (existingResourceWithEmail != null && existingResourceWithEmail.getResourceId() != existingResource.getResourceId()) {
//				// If a resource with the same emailId exists and it's not the one being
//				// updated, throw an exception
//				throw new EmailAlreadyExistException("Another Resource exists with given Email : " + resource.getEmailId());
//			}
//			
//			
//			existingResource.setEmailId(resource.getEmailId());
//			existingResource.setFirstName(resource.getFirstName());
//			existingResource.setLastName(resource.getLastName());
//			existingResource.setPhoneNumber(resource.getPhoneNumber());
//			existingResource.setAddressLine1(resource.getAddressLine1());
//			existingResource.setAddressLine2(resource.getAddressLine2());
//			existingResource.setCity(resource.getCity());
//			existingResource.setState(resource.getState());
//			existingResource.setZipcode(resource.getZipcode());
//			existingResource.setCountry(resource.getCountry());
//			existingResource.setActive(resource.getActive());
//			existingResource.setPassword(resource.getPassword());
//			existingResource.setRole(resource.getRole());
//			existingResource.setModifiedBy(resource.getModifiedBy());
//			Resource updatedResource = resourceRepo.save(existingResource);
//
//			if (updatedResource != null) {
//				// Convert updated Resource entity back to ResourceDTO
//				ResourceDTO updatedResourceDTO = modelMapper.map(updatedResource, ResourceDTO.class);
//				// Return the updated ResourceDTO
//				return updatedResourceDTO;
//			}
//
//			// return updatedResource;
//		}
//		
//		return null;
//
//	}
//
//	@Override
//	public ResourceDTO loginResourceByEmailId_Password(String emailId, String password) {
//		// TODO Auto-generated method stub
//		Resource resource = resourceRepo.findByEmailId(emailId);
//		if (resource != null) {
//			// Check if the password matches
//			if (resource.getPassword().equals(password)) {
//				// If the password matches, return the resourceDTO
//				ResourceDTO resourceDTO = modelMapper.map(resource, ResourceDTO.class);
//				return resourceDTO;
//			}
//			return null; // If the password does not match, return null;
//		}
//		// If no resource is found with the given emailId, return null
//		return null;
//	}
//
//	@Override
//	public Resource getResourceById(Integer resourceId) {
//		// TODO Auto-generated method stub
//		Resource resource = resourceRepo.findById(resourceId)
//				.orElseThrow(() -> new ResourceNotFound("Resource", "resourceId", resourceId));
//		
//	   return resource;
//	}
//
//	@Override
//	public List<Resource> getAllResources() {
//		// TODO Auto-generated method stub
//		List<Resource> resources = resourceRepo.findAll();
//		if (resources != null && !resources.isEmpty()) {
//			
//			return resources;
//		}
//		return null;
//	}
//
//}


package com.wtt.TimetraxRestApis.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wtt.TimetraxRestApis.dto.ResourceDTO;
import com.wtt.TimetraxRestApis.entity.Resource;
import com.wtt.TimetraxRestApis.exception.EmailAlreadyExistException;
import com.wtt.TimetraxRestApis.exception.ResourceNotFound;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepo resourceRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResourceDTO createResource(ResourceDTO resourceDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Resource resource = modelMapper.map(resourceDTO, Resource.class);
		
		if (resourceRepo.findByEmailId(resource.getEmailId()) != null) {
			throw new EmailAlreadyExistException("Resource already exists with given Email : " +
							 resource.getEmailId());
		}

		Resource savedResource = resourceRepo.save(resource);

		if (savedResource != null) {
			ResourceDTO savedResourceDTO = modelMapper.map(savedResource, ResourceDTO.class);
			return savedResourceDTO;
		}

		return null;
	}

	@Override
	public ResourceDTO updateResource(ResourceDTO resourceDTO, int resourceId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Resource resource = modelMapper.map(resourceDTO, Resource.class);
		resource.setResourceId(resourceId);
		
		Resource existingResource = resourceRepo.findById(resource.getResourceId()).orElseThrow(
				()-> new ResourceNotFound("Resource", "resourceId", resourceId)
				);
		
		if (existingResource != null) {
			Resource existingResourceWithEmail = resourceRepo.findByEmailId(resource.getEmailId());
			if (existingResourceWithEmail != null && existingResourceWithEmail.getResourceId() != existingResource.getResourceId()) {
				throw new EmailAlreadyExistException("Another Resource exists with given Email : " + resource.getEmailId());
			}
			
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
			
			// Only update password if it's provided and not empty
			if (resource.getPassword() != null && !resource.getPassword().isEmpty()) {
				existingResource.setPassword(resource.getPassword());
			}
			
			existingResource.setRole(resource.getRole());
			existingResource.setModifiedBy(resource.getModifiedBy());
			
			Resource updatedResource = resourceRepo.save(existingResource);

			if (updatedResource != null) {
				ResourceDTO updatedResourceDTO = modelMapper.map(updatedResource, ResourceDTO.class);
				return updatedResourceDTO;
			}
		}
		
		return null;
	}

	@Override
	@Deprecated
	public ResourceDTO loginResourceByEmailId_Password(String emailId, String password) {
		Resource resource = resourceRepo.findByEmailId(emailId);
		if (resource != null && resource.getActive()) {
			// Use password encoder for validation
			if (passwordEncoder.matches(password, resource.getPassword())) {
				ResourceDTO resourceDTO = modelMapper.map(resource, ResourceDTO.class);
				return resourceDTO;
			}
		}
		return null;
	}

	@Override
	public Resource getResourceById(Integer resourceId) {
		Resource resource = resourceRepo.findById(resourceId)
				.orElseThrow(() -> new ResourceNotFound("Resource", "resourceId", resourceId));
		
		return resource;
	}

	@Override
	public Resource getResourceByEmailId(String emailId) {
		Resource resource = resourceRepo.findByEmailId(emailId);
		if (resource == null) {
			throw new ResourceNotFound("Resource", "emailId", 0);
		}
		return resource;
	}

	@Override
	public List<Resource> getAllResources() {
		List<Resource> resources = resourceRepo.findAll();
		if (resources != null && !resources.isEmpty()) {
			return resources;
		}
		return null;
	}

	@Override
	public boolean isResourceOwner(String emailId, Integer resourceId) {
		try {
			Resource resource = resourceRepo.findByEmailId(emailId);
			return resource != null && resource.getResourceId().equals(resourceId);
		} catch (Exception e) {
			return false;
		}
	}
}
