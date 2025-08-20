package com.wtt.TimetraxRestApis.service;

import com.wtt.TimetraxRestApis.dto.AssignResourceDTO;
import com.wtt.TimetraxRestApis.entity.AssignResource;
import com.wtt.TimetraxRestApis.entity.Project;
import com.wtt.TimetraxRestApis.exception.ResourceNotFound;
import com.wtt.TimetraxRestApis.repository.AssignResourceRepository;
import com.wtt.TimetraxRestApis.repository.ProjectRepo;
import com.wtt.TimetraxRestApis.repository.ResourceRepo;

import lombok.RequiredArgsConstructor;
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
}
