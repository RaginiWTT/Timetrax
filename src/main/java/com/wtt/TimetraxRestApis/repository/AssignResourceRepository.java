package com.wtt.TimetraxRestApis.repository;


import com.wtt.TimetraxRestApis.entity.AssignResource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignResourceRepository extends JpaRepository<AssignResource, Integer> {
	
	  List<AssignResource> findByProjectId(Integer projectId);
	    List<AssignResource> findByResourceId(Integer resourceId);
	  //  List<AssignResource> findByResourceIdAndActiveStatus(Integer resourceId, Boolean activeStatus);
		List<AssignResource> findByProjectIdAndResourceId(Integer projectId, Integer resourceId);
}

