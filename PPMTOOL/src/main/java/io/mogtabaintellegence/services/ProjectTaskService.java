package io.mogtabaintellegence.services;

import io.mogtabaintellegence.domain.Backlog;
import io.mogtabaintellegence.domain.ProjectTask;
import io.mogtabaintellegence.repositories.BacklogRepository;
import io.mogtabaintellegence.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Interface 3 on 2/15/2020.
 */
@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask){
        //Exception : Project not found
        //Pts to be added to a specific project , project ! = null,Bl exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
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
      if (projectTask.getPriority()==null){
             // in the future we want this projectTask.getPriority() == 0 to handle form.
             projectTask.setPriority(3);
        }
        //Initial status when status is null
        if (projectTask.getStatus()=="" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);
    }
}
