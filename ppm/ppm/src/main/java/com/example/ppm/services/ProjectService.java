package com.example.ppm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppm.domain.Project;
import com.example.ppm.exceptions.ProjectIdException;
import com.example.ppm.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;


	public Project saveOrUpdateProject(Project project) {
	try {
		project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
		return	projectRepository.save(project);
	}catch(Exception e) {
		throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		
	}
		
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project Id '" + projectId + "' does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException("Project Id '" + projectId + "' does not exist and thus can't be deleted");
		}
	 projectRepository.delete(project);
	}
}
