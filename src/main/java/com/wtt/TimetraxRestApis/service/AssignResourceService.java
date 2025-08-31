package com.wtt.TimetraxRestApis.service;

import java.util.List;

import com.wtt.TimetraxRestApis.dto.AssignResourceDTO;

public interface AssignResourceService {
	AssignResourceDTO assignResourceToProject(AssignResourceDTO dto);

	List<AssignResourceDTO> getAllAssignedResources();

	List<AssignResourceDTO> getAssignedResourcesByProject(Integer projectId);

	List<AssignResourceDTO> getAssignedResourcesByResource(Integer resourceId);

	AssignResourceDTO getAssignedResourceById(Integer id);

	AssignResourceDTO updateAssignment(Integer id, AssignResourceDTO dto);

}
