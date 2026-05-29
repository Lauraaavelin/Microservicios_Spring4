package com.projectservice.controller;

import com.projectservice.model.Project;
import com.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody Project project){

        return ResponseEntity.ok(
                projectService.createProject(project)
        );
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(){

        return ResponseEntity.ok(
                projectService.getAllProjects()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable String id){

        return ResponseEntity.ok(
                projectService.getProjectById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable String id,
            @RequestBody Project project){

        return ResponseEntity.ok(
                projectService.updateProject(id, project)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(
            @PathVariable String id){

        projectService.deleteProject(id);

        return ResponseEntity.ok(
                Map.of("message", "Project deleted")
        );
    }

    @GetMapping("/{id}/saving")
    public ResponseEntity<?> getSaving(
            @PathVariable String id){

        Double totalSaving =
                projectService.getProjectSaving(id);

        return ResponseEntity.ok(
                Map.of(
                        "projectId", id,
                        "totalSaving", totalSaving
                )
        );
    }
}
