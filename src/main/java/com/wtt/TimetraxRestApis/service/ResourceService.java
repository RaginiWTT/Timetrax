package com.wtt.TimetraxRestApis.service;

import com.wtt.TimetraxRestApis.dto.ResourceDTO;
import com.wtt.TimetraxRestApis.entity.Resource;

public interface ResourceService {
	ResourceDTO createResource(ResourceDTO resourceDTO);

  Resource updateResource(Resource resource);
  
  Resource loginResourceByEmailId_Password(String emailId, String password);
}
