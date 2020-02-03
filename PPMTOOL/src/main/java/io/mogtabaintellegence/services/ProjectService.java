package io.mogtabaintellegence.services;

import io.mogtabaintellegence.domain.Project;
import io.mogtabaintellegence.exceptions.ProjectIdException;
import io.mogtabaintellegence.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Interface 3 on 2/2/2020.
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project SaveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
           return projectRepository.save(project);
        }catch (Exception e) {

            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");

        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exists");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

}
