//package com.wtt.TimetraxRestApis.service;
//
//import java.util.List;
//
//import com.wtt.TimetraxRestApis.dto.ResourceDTO;
//import com.wtt.TimetraxRestApis.entity.Resource;
//
//public interface ResourceService {
//	ResourceDTO createResource(ResourceDTO resourceDTO);
//
//	ResourceDTO updateResource(ResourceDTO resourceDTO, int resourceId);
//
//	ResourceDTO loginResourceByEmailId_Password(String emailId, String password);
//
//	Resource getResourceById(Integer resourceId);
//
//	List<Resource> getAllResources();
//
//}

package com.wtt.TimetraxRestApis.service;

import java.util.List;

import com.wtt.TimetraxRestApis.dto.ResourceDTO;
import com.wtt.TimetraxRestApis.entity.Resource;

public interface ResourceService {
	ResourceDTO createResource(ResourceDTO resourceDTO);

	ResourceDTO updateResource(ResourceDTO resourceDTO, int resourceId);

	ResourceDTO loginResourceByEmailId_Password(String emailId, String password);

	Resource getResourceById(Integer resourceId);
	
	Resource getResourceByEmailId(String emailId);

	List<Resource> getAllResources();
	
	boolean isResourceOwner(String emailId, Integer resourceId);

}
