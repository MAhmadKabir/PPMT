package com.example.ppm.web;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ppm.domain.ProjectTask;
import com.example.ppm.services.MapValidationErrorService;
import com.example.ppm.services.ProjectTaskService;

@Controller
@CrossOrigin
@RequestMapping("/api/backlog")
public class BacklogController {

	@Autowired
	private ProjectTaskService projectTaskService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result, 
			@PathVariable String backlog_id,Principal principal ){
		
		ResponseEntity<?> errormap = mapValidationErrorService.MapValidationService(result);
		if(errormap!= null) { return errormap;}
		
		ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask, principal.getName());
		return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);	
	}
	@PostMapping("bulk/{backlog_id}")
	public ResponseEntity<?> addBulkPTtoBacklog(@Valid @RequestBody List<ProjectTask> projectTask, BindingResult result,
			@PathVariable String backlog_id,Principal principal ){
		
		ResponseEntity<?> errormap = mapValidationErrorService.MapValidationService(result);
		if(errormap!= null) { return errormap;}
		
		List<ProjectTask> projectTask1 = projectTaskService.addBulkProjectTask(backlog_id, projectTask, principal.getName());
		return new ResponseEntity <List<ProjectTask>>(projectTask1, HttpStatus.CREATED);	
	}
	@GetMapping("/{backlog_id}")
//	public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){
//		return projectTaskService.findBacklogById(backlog_id);
//	}
	public ResponseEntity<?> getProjectBacklog(@PathVariable String backlog_id, Principal principal){
		Iterable<ProjectTask> Tlist=	projectTaskService.findBacklogById(backlog_id,principal.getName());
		return  new ResponseEntity<Iterable>(Tlist, HttpStatus.OK);
	}
	@GetMapping("/{backlog_id}/{pt_id}")
	 public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id, Principal principal){
        ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id, principal.getName());

		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@PatchMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result,
		    @PathVariable String backlog_id, @PathVariable String pt_id, Principal principal ){

		ResponseEntity<?> errormap = mapValidationErrorService.MapValidationService(result);
		if(errormap!= null) { return errormap;}
		
		  ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask,backlog_id,pt_id, principal.getName());

		return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlog_id}/{pt_id}")
//	public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
//	projectTaskService.deletePTByProjectSequence(backlog_id, pt_id);
	 public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id, Principal principal){
        projectTaskService.deletePTByProjectSequence(backlog_id, pt_id, principal.getName());

	return new ResponseEntity<String>("Project task'"+pt_id+"'deleted successfully!", HttpStatus.OK);
	}
}
