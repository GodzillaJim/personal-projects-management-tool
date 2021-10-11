package com.example.projectmanagementtool.controllers;

import com.example.projectmanagementtool.domain.Project;
import com.example.projectmanagementtool.services.ProjectService;
import com.example.projectmanagementtool.services.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;
    private final ValidationService validationService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        ResponseEntity<?> validationResult =
                validationService.mapValidationService(result);
        if (validationResult != null)
                return validationResult;
        return new ResponseEntity<>(projectService.saveOrUpdateProject(project),
                HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }
    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<Project> getProjectByProjectIdentifier(@PathVariable String projectIdentifier){
        Project project = projectService.findProjectByProjectIdentifier(projectIdentifier);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
