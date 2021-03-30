package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Project;
import com.example.demo.exceptions.ProjectIdentifierException;
import com.example.demo.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
			
		}
		catch(Exception e) {
			throw new ProjectIdentifierException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}
		
		
		
	}
	
	public Project findProjectByIdenifier(String projectIdenifier) {
		
		Project project = projectRepository.findByProjectIdentifier(projectIdenifier.toUpperCase());
		
		if (project == null) {
			throw new ProjectIdentifierException("Project does not exist");
		}
		
		return project;
		
		
	}
	
}