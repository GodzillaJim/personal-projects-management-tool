package com.example.projectmanagementtool.services;

import com.example.projectmanagementtool.domain.Project;
import com.example.projectmanagementtool.exceptions.ProjectIDException;
import com.example.projectmanagementtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIDException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists!");
        }
    }
}
