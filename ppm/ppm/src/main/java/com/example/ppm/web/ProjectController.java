package com.example.ppm.web;

import java.util.List;
import java.security.Principal;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;

import com.example.ppm.domain.Project;
import com.example.ppm.services.MapValidationErrorService;
import com.example.ppm.services.ProjectService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin 
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	//Binding result is an interface that involves validation on an object, it analyzes an object and determines
	//whether there are any errors or not
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal)
{
		ResponseEntity<?> mapErrors = mapValidationErrorService.MapValidationService(result);
		if(mapErrors != null) {return mapErrors;}
		Project project1 = projectService.saveOrUpdateProject(project , principal.getName());
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectId(@PathVariable String projectId,Principal principal){
		Project project = projectService.findProjectByIdentifier(projectId,principal.getName());
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(Principal principal){
		return projectService.findAllProjects(principal.getName());
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId,Principal principal){
		projectService.deleteProjectByIdentifier(projectId,principal.getName());
		return new ResponseEntity<String>("Project Id '"+projectId+"' was deleted", HttpStatus.OK);
		
	}
}
