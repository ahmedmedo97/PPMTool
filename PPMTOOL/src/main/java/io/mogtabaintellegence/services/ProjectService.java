package io.mogtabaintellegence.services;

import io.mogtabaintellegence.domain.Backlog;
import io.mogtabaintellegence.domain.Project;
import io.mogtabaintellegence.domain.User;
import io.mogtabaintellegence.exceptions.ProjectIdException;
import io.mogtabaintellegence.exceptions.ProjectNotFoundException;
import io.mogtabaintellegence.repositories.BacklogRepository;
import io.mogtabaintellegence.repositories.ProjectRepository;
import io.mogtabaintellegence.repositories.UserRepository;
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
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project SaveOrUpdateProject(Project project,String username) {

        if (project.getId() !=null){
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if (existingProject !=null && (!existingProject.getProjectLeader().equals(username))){
                throw new ProjectNotFoundException("Project not found in your account");
            }else if (existingProject == null){
                throw new ProjectNotFoundException("Project with ID: '"+ project.getProjectIdentifier()+"' cannot be updated because" +
                        " it doesn't exist");
            }
        }

        try {
            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            if (project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }
           return projectRepository.save(project);
        }catch (Exception e) {

            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");

        }
    }

    public Project findProjectByIdentifier(String projectId,String username) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exists");
        }
        if (!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Project not found in your account");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(String username){
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectId,String username){

        projectRepository.delete(findProjectByIdentifier(projectId,username));

    }

}
