package com.wtt.TimetraxRestApis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.Resource;

public interface ResourceRepo extends JpaRepository<Resource, Integer> {
	public Resource findByEmailId(String emailId);


}
