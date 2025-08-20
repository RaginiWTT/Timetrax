package com.wtt.TimetraxRestApis.service;

import com.wtt.TimetraxRestApis.dto.AssignResourceDTO;
import com.wtt.TimetraxRestApis.entity.AssignResource;
import com.wtt.TimetraxRestApis.entity.Project;
import com.wtt.TimetraxRestApis.exception.ResourceNotFound;
import com.wtt.TimetraxRestApis.repository.AssignResourceRepository;
import com.wtt.TimetraxRestApis.repository.ProjectRepo;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignResourceServiceImpl implements AssignResourceService {

	@Autowired
	private AssignResourceRepository assignResourceRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	ProjectRepo projectRepository;
	@Autowired
	ResourceRepo resourceRepository;

	@Override
	public AssignResourceDTO assignResourceToProject(AssignResourceDTO dto) {
		// tight mapping of modelmapper
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

//		if (!projectRepository.existsById(dto.getProjectId())) {
//		    throw new ResourceNotFound("Project", "ProjectId", dto.getProjectId());
//		}
		// Check if the project exists
		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new ResourceNotFound("Project", "ProjectId", dto.getProjectId()));

//		if (!resourceRepository.existsById(dto.getResourceId())) {
//		    throw new ResourceNotFound("Resource", "ResourceId", dto.getResourceId());
//		}

		// Check if the resource exists
		com.wtt.TimetraxRestApis.entity.Resource resource = resourceRepository.findById(dto.getResourceId())
				.orElseThrow(() -> new ResourceNotFound("Resource", "ResourceId", dto.getResourceId()));

		// Skip the id field in AssignResource during mapping

		//
		modelMapper.typeMap(AssignResourceDTO.class, AssignResource.class)
				.addMappings(mapper -> mapper.skip(AssignResource::setId));

		// Convert DTO -> Entity
		AssignResource entity = modelMapper.map(dto, AssignResource.class);

		// Save entity
		AssignResource saved = assignResourceRepository.save(entity);

		// Convert back to DTO
		AssignResourceDTO savedDto = modelMapper.map(saved, AssignResourceDTO.class);
		savedDto.setProjectName(project.getProjectName());
		savedDto.setResourceName(resource.getFirstName() + " " + resource.getLastName());
		return savedDto;
	}

//	public List<AssignResourceDTO> getAllAssignedResources() {
//        return assignResourceRepository.findAll()
//                .stream()
//                .map(assignResource -> modelMapper.map(assignResource, AssignResourceDTO.class))
//                .collect(Collectors.toList());
//    }

	public List<AssignResourceDTO> getAllAssignedResources() {
		return assignResourceRepository.findAll().stream().map(assignResource -> {
			AssignResourceDTO dto = modelMapper.map(assignResource, AssignResourceDTO.class);

			Project project = projectRepository.findById(dto.getProjectId())
					.orElseThrow(() -> new ResourceNotFound("Project", "ProjectId", dto.getProjectId()));

			com.wtt.TimetraxRestApis.entity.Resource resource = resourceRepository.findById(dto.getResourceId())
					.orElseThrow(() -> new ResourceNotFound("Resource", "ResourceId", dto.getResourceId()));

			dto.setProjectName(project.getProjectName());
			dto.setResourceName(resource.getFirstName() + " " + resource.getLastName());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<AssignResourceDTO> getAssignedResourcesByProject(Integer projectId) {
		return assignResourceRepository.findByProjectId(projectId).stream()
				.map(resource -> modelMapper.map(resource, AssignResourceDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<AssignResourceDTO> getAssignedResourcesByResource(Integer resourceId) {
		return assignResourceRepository.findByResourceId(resourceId).stream()
				.map(resource -> modelMapper.map(resource, AssignResourceDTO.class)).collect(Collectors.toList());
	}

	@Override
	public AssignResourceDTO updateAssignment(Integer id, AssignResourceDTO dto) {
		AssignResource existing = assignResourceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("AssignResource", "Id", id));

		// Update fields
		existing.setProjectId(dto.getProjectId());
		existing.setResourceId(dto.getResourceId());
		existing.setFromDate(dto.getFromDate());
		existing.setToDate(dto.getToDate());
		existing.setModifiedBy(dto.getModifiedBy());

		AssignResource saved = assignResourceRepository.save(existing);

		// Convert back to DTO
		AssignResourceDTO response = modelMapper.map(saved, AssignResourceDTO.class);

		// Set projectName
		projectRepository.findById(saved.getProjectId())
				.ifPresent(project -> response.setProjectName(project.getProjectName()));

		// Set resourceName
		resourceRepository.findById(saved.getResourceId()).ifPresent(
				resource -> response.setResourceName(resource.getFirstName() + " " + resource.getLastName()));

		return response;
	}

	@Override
	public AssignResourceDTO getAssignedResourceById(Integer id) {

		AssignResource assignResource = assignResourceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("AssignResource", "Id", id));

		// Convert to DTO
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AssignResourceDTO assignResourceDTO = modelMapper.map(assignResource, AssignResourceDTO.class);
		// Set projectName
		projectRepository.findById(assignResource.getProjectId())
				.ifPresent(project -> assignResourceDTO.setProjectName(project.getProjectName()));

		// Set resourceName
		resourceRepository.findById(assignResource.getResourceId()).ifPresent(
				resource -> assignResourceDTO.setResourceName(resource.getFirstName() + " " + resource.getLastName()));

		return assignResourceDTO;
	}
}
