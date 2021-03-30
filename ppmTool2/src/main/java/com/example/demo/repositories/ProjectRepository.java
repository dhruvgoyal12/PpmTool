package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	

}
