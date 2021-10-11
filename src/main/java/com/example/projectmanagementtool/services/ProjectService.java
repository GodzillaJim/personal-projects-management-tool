package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.domain.Project;
import com.example.projectmanagementtool.exceptions.ProjectIDException;
import com.example.projectmanagementtool.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIDException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists!");
        }
    }
    public Project findProjectByProjectIdentifier(String projectIdentifier){
        Project project = projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIDException("Project does not exist");
        }
        return project;
    }
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }
    public void deleteProjectByProjectIdentifier(String projectIdentifier){
        Project project = projectRepository.findProjectByProjectIdentifier(projectIdentifier);
        if(project == null){
            throw new ProjectIDException("Cannot find Project with ID '" + projectIdentifier + "'. Project does not exist!");
        }
        projectRepository.delete(project);
    }
}
