package com.example.ppm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ppm.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

	//     Iterable<Project> findAllById(Iterable<Long> ids) ;
	Project findByProjectIdentifier (String projectId);

	
	 Iterable<Project> findAll();	
	
}
