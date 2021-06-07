package com.example.demo.services;

import com.example.demo.domain.Backlog;
import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectTask;
import com.example.demo.exceptions.ProjectNotFoundException;
import com.example.demo.repositories.BacklogRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

      try {
          // Exceptions : Project not found
          //PTs to be added to a specific project, project != null, BL exists
          Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);




          // set the bl to pt
          projectTask.setBacklog(backlog);

          //we want our project sequence to be like this: IDPRO-1 IDPRO-2
          Integer backlogSequence = backlog.getPTSequence();


          // update the BLSequence
          backlogSequence++;

          backlog.setPTSequence(backlogSequence);
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
      }catch (Exception e){
          throw new ProjectNotFoundException("Project Not Found ");
      }

    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {

        Project project = projectRepository.findByProjectIdentifier(backlog_id);

        if (project == null){
            throw new ProjectNotFoundException("Project with ID: "+ backlog_id + " does not exists");
        }


        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);


    }


    public ProjectTask findPTByProjectSequence(String backlog_id, String sequence){
        // make sure the backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);

        if(backlog == null) throw new ProjectNotFoundException("Project with ID: "+ backlog_id + " does not exists");

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);

        if (projectTask == null) throw new ProjectNotFoundException("Project Task " + sequence + " not found");

        // make sure we are searching on the right backlog
        if(!projectTask.getProjectIdentifier().equals(backlog_id)) throw new ProjectNotFoundException("Project Task " + sequence + " does not exist in project " + backlog_id);

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String sequence){

        //find Existing project task
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, sequence);

        // replace it with updated task
        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    public void deleteByProjectSequence(String backlog_id, String sequence){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, sequence);
        projectTaskRepository.delete(projectTask);
    }
}
