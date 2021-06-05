package com.example.demo.services;

import com.example.demo.domain.Backlog;
import com.example.demo.repositories.BacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Project;
import com.example.demo.exceptions.ProjectIdentifierException;
import com.example.demo.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;
	
	public Project saveOrUpdateProject(Project project) {
		String projectIdentifier = project.getProjectIdentifier().toUpperCase();
		try {
			project.setProjectIdentifier(projectIdentifier);
			if(project.getId() == null){
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(projectIdentifier);
			}
			else{
				project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
			}
			return projectRepository.save(project);
			
		}
		catch(Exception e) {
			throw new ProjectIdentifierException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}
		
		
		
	}
	
	public Project findProjectByIdenifier(String projectIdentifier) {
		
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if (project == null) {
			throw new ProjectIdentifierException("Project does not exist");
		}
		
		return project;
		
		
	}
	
	
	public Iterable<Project> findAllProjects(){
		Iterable<Project> allProjects = projectRepository.findAll();
		
		return allProjects;
	}
	
	
	public void deleteProjectByIdentifier(String projectIdentifier) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		
		if (project == null) {
			throw new ProjectIdentifierException("Cannot delete Project '" + projectIdentifier + "' as it does not exists");
		}
		
		projectRepository.delete(project);
		
		
	}
	
}
