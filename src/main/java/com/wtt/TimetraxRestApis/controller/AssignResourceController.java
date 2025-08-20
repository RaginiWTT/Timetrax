package com.wtt.TimetraxRestApis.controller;

import com.wtt.TimetraxRestApis.dto.AssignResourceDTO;
import com.wtt.TimetraxRestApis.service.AssignResourceService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assign-resource")
@RequiredArgsConstructor
public class AssignResourceController {

	@Autowired
    private AssignResourceService assignResourceService;

    @PostMapping
    public ResponseEntity<AssignResourceDTO> assignResource(@RequestBody AssignResourceDTO dto) {
        AssignResourceDTO saved = assignResourceService.assignResourceToProject(dto);
        return ResponseEntity.ok(saved);
    }
}

