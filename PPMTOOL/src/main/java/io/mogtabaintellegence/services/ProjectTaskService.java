package io.mogtabaintellegence.services;

import io.mogtabaintellegence.domain.Backlog;
import io.mogtabaintellegence.domain.Project;
import io.mogtabaintellegence.domain.ProjectTask;
import io.mogtabaintellegence.exceptions.ProjectNotFoundException;
import io.mogtabaintellegence.repositories.BacklogRepository;
import io.mogtabaintellegence.repositories.ProjectRepository;
import io.mogtabaintellegence.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Interface 3 on 2/15/2020.
 */
@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask,String username){

            Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier,username).getBacklog();              //backlogRepository.findByProjectIdentifier(projectIdentifier);
            //set bl to Pt
            projectTask.setBacklog(backlog);
            //we want our project sequence to be like this:  IDPRO-1 , IDPRO-2
            Integer BacklogSequence = backlog.getPTSequence();
            //update the BL sequence
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);
            //Add Sequence to project task
            projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);
            //Initial priority when priority is null
            if ( projectTask.getPriority()==null || projectTask.getPriority() == 0){
                // in the future we want this projectTask.getPriority() == 0 to handle form.
                projectTask.setPriority(3);
            }
            //Initial status when status is null
            if (projectTask.getStatus()=="" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);


    }

    public Iterable<ProjectTask> findBacklogById(String id,String username) {
       projectService.findProjectByIdentifier(id,username);
     return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
    public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id,String username){
        // make sure we are searching on an existing backlog
        projectService.findProjectByIdentifier(backlog_id,username);

        //make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task with ID: '" + pt_id + "'  not found");
        }
        //make sure that the backlog/project id in the path corresponds to the right project.
        if (!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task with ID: '" + pt_id + "' does not exist in project : " + backlog_id);
        }
        return projectTask;

    }


    public ProjectTask updateByProjectSequence(ProjectTask updatedTask,String backlog_id,String pt_id,String username)
    {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id,username);
        projectTask = updatedTask;
        return  projectTaskRepository.save(projectTask);

    }

    public void deletePTByProjectSequence(String backlog_id,String pt_id,String username){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id,username);
        projectTaskRepository.delete(projectTask);

    }













}
