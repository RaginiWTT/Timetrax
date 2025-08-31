package com.wtt.TimetraxRestApis.controller;

import com.wtt.TimetraxRestApis.dto.AssignResourceDTO;
import com.wtt.TimetraxRestApis.service.AssignResourceService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    @GetMapping("/all")
    public ResponseEntity<List<AssignResourceDTO>> getAllAssignedResources() {
    	List<AssignResourceDTO> assignedResources = assignResourceService.getAllAssignedResources();
        return ResponseEntity.ok(assignedResources);
    }

    // Get assigned resources by ProjectId
    @GetMapping("/project/{projectId}")
    public List<AssignResourceDTO> getAssignedResourcesByProject(@PathVariable Integer projectId) {
        return assignResourceService.getAssignedResourcesByProject(projectId);
    }

    // Get assigned resources by ResourceId
//    @GetMapping("/resource/{resourceId}")
//    public List<AssignResourceDTO> getAssignedResourcesByResource(@PathVariable Integer resourceId) {
//        return assignResourceService.getAssignedResourcesByResource(resourceId);
//    }
    
//    @GetMapping("/resource/{resourceId}")
//    public Map<Integer,String> getAssignedResourcesByResource(@PathVariable Integer resourceId) {
//        List<AssignResourceDTO> assignResourceDTOs= assignResourceService.getAssignedResourcesByResource(resourceId);
//        
//		for (AssignResourceDTO dto : assignResourceDTOs) {
//			System.out.println("Project ID: " + dto.getProjectId() + " Project Name: " + dto.getProjectName());
//		}
//
//		if (!assignResourceDTOs.isEmpty()) {
//			return assignResourceDTOs.stream()
//					.collect(java.util.stream.Collectors.toMap(AssignResourceDTO::getProjectId,
//							AssignResourceDTO::getProjectName, (existing, replacement) -> existing // In case of key
//																									// collision, keep
//																									// the existing
//																									// value
//					));
//		}
//        
//        return null;
//    }
    
    @GetMapping("/resource/{resourceId}")
    public Set<AssignResourceDTO> getAssignedResourcesByResource(@PathVariable Integer resourceId) {
        List<AssignResourceDTO> assignResourceDTOs= assignResourceService.getAssignedResourcesByResource(resourceId);

		if (!assignResourceDTOs.isEmpty()) {
			return assignResourceDTOs.stream().collect(java.util.stream.Collectors.toSet());
		}
        
        return null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AssignResourceDTO> updateAssignment(
            @PathVariable Integer id,
            @RequestBody AssignResourceDTO dto) {
        AssignResourceDTO updated = assignResourceService.updateAssignment(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    // Get assigned resource by ID
    @GetMapping("/{id}")
    public ResponseEntity<AssignResourceDTO> getAssignedResourceById(@PathVariable Integer id) {
        AssignResourceDTO assignedResource = assignResourceService.getAssignedResourceById(id);
        return ResponseEntity.ok(assignedResource);
    }
    
 
    
}

