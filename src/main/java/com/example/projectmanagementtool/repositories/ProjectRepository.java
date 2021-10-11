package com.example.projectmanagementtool.repositories;

import com.example.projectmanagementtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    Iterable<Project> findAll();
    Project findProjectByProjectIdentifier(String projectIdentifier);

}
