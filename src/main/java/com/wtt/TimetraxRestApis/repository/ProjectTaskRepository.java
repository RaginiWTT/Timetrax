package com.wtt.TimetraxRestApis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtt.TimetraxRestApis.entity.ProjectTask;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Integer> {
	List<ProjectTask> findByProject_ProjectIdAndActiveTrue(Integer projectId);
}

