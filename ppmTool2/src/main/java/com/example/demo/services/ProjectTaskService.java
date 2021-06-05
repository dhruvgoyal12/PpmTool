package com.example.demo.services;

import com.example.demo.domain.Backlog;
import com.example.demo.domain.ProjectTask;
import com.example.demo.repositories.BacklogRepository;
import com.example.demo.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        // Exceptions : Project not found
        //PTs to be added to a specific project, project != null, BL exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

        // set the bl to pt
        projectTask.setBacklog(backlog);

        //we want our project sequence to be like this: IDPRO-1 IDPRO-2
        Integer backlogSequence = backlog.getPTSequence();


        // update the BLSequence
        backlogSequence++;

        // Add Sequence to Project task
        projectTask.setProjectSequence(projectIdentifier+ "-" +backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        //INITIAL priority when priority is null
        if(projectTask.getPriority() == null || projectTask.getPriority() == 0){
            projectTask.setPriority(3);
        }


        //INTITIAL status when status is null
        if (projectTask.getStatus() == null || projectTask.getStatus() == ""){
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);

    }
}
