package com.wtt.TimetraxRestApis.repository;


import com.wtt.TimetraxRestApis.entity.AssignResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignResourceRepository extends JpaRepository<AssignResource, Integer> {
}

