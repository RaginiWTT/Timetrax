package com.wtt.TimetraxRestApis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wtt.TimetraxRestApis.entity.ProjectTask;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Integer> {
	List<ProjectTask> findByProject_ProjectIdAndActiveTrue(Integer projectId);
	
	@Modifying
	@Query(value = """
	    UPDATE ProjectTask
	    SET Active = :active
	    WHERE TaskId IN (
	        SELECT TaskId FROM TimesheetLine WHERE TimesheetId = :timesheetId
	    )
	    """, nativeQuery = true)
	int updateActiveFlagForTasks(@Param("timesheetId") Integer timesheetId,
	                             @Param("active") Integer active);

}

