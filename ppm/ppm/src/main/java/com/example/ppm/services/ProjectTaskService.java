package com.example.ppm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ppm.domain.Backlog;
import com.example.ppm.domain.Project;
import com.example.ppm.domain.ProjectTask;
import com.example.ppm.exceptions.ProjectNotFoundException;
import com.example.ppm.repositories.BacklogRepository;
import com.example.ppm.repositories.ProjectRepository;
import com.example.ppm.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private ProjectTaskRepository  projectTaskRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
    @Autowired
    private ProjectService projectService;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username){

//    	Backlog backlog =backlogRepository.findByProjectIdentifier(projectIdentifier);
    	 Backlog backlog =  projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog(); //backlogRepository.findByProjectIdentifier(projectIdentifier);	
			//set the BL to project task
			projectTask.setBacklog(backlog);
			// we want our sequence to be like this ACSD-1
			Integer BackLogSequence = backlog.getPTSequence();
			//update sequence
			BackLogSequence++;
			backlog.setPTSequence(BackLogSequence);
			
			//Add sequence to project task
			projectTask.setProjectSequence(projectIdentifier+"-"+BackLogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			
			//initial priority when priority is null 
	         if(projectTask.getPriority()==null||projectTask.getPriority()==0){ //In the future we need projectTask.getPriority()== 0 to handle the form
				projectTask.setPriority(3);
			}
			//initial Status when null
			if(projectTask.getStatus()=="" || projectTask.getStatus()==null) {
				projectTask.setStatus("TO_DO");
			}
			return projectTaskRepository.save(projectTask);
			}
    
    public List<ProjectTask> addBulkProjectTask(String projectIdentifier, List<ProjectTask> projectTask, String username){

//    	Backlog backlog =backlogRepository.findByProjectIdentifier(projectIdentifier);
//    	 Backlog backlog =  projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog(); //backlogRepository.findByProjectIdentifier(projectIdentifier);	
			//set the BL to project task
			
    	 
//    	 projectTask.setBacklog(backlog);
			// we want our sequence to be like this ACSD-1
//			Integer BackLogSequence = backlog.getPTSequence();
			//update sequence
//			BackLogSequence++;
//			backlog.setPTSequence(BackLogSequence);
			
			//Add sequence to project task
//			projectTask.setProjectSequence(projectIdentifier+"-"+BackLogSequence);
//			projectTask.setProjectIdentifier(projectIdentifier);
			
			//initial priority when priority is null 
//	         if(projectTask.getPriority()==null||projectTask.getPriority()==0){ //In the future we need projectTask.getPriority()== 0 to handle the form
//				projectTask.setPriority(3);
//			}
			//initial Status when null
//			if(projectTask.getStatus()=="" || projectTask.getStatus()==null) {
//				projectTask.setStatus("TO_DO");
//			}
//			return projectTaskRepository.save(projectTask);
    	 projectTask.stream().forEach(pt ->{
    	 	 Backlog backlog =  projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog(); //backlogRepository.findByProjectIdentifier(projectIdentifier);
    		 pt.setBacklog(backlog); 	
    	 Integer BackLogSequence = backlog.getPTSequence();
			//update sequence
			BackLogSequence++;
			backlog.setPTSequence(BackLogSequence);
			//Add sequence to project task
			pt.setProjectSequence(projectIdentifier+"-"+BackLogSequence);
			pt.setProjectIdentifier(projectIdentifier);
			//initial priority when priority is null 
	         if(pt.getPriority()==null||pt.getPriority()==0){ //In the future we need projectTask.getPriority()== 0 to handle the form
				pt.setPriority(3);
			}
	 		if(pt.getStatus()=="" || pt.getStatus()==null) {
				pt.setStatus("TO_DO");
			}
		

    	 } );
//    	 return projectTask;
return (List<ProjectTask>) projectTaskRepository.saveAll( projectTask);	
    }
	
//	 public Iterable<ProjectTask>findBacklogById(String id){
//		 Project project = projectRepository.findByProjectIdentifier(id);
//		 if(project==null) {
//			 throw new ProjectNotFoundException("Project with id '"+id+"' does not exist");
//		 }
//	        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
//	    }
    public Iterable<ProjectTask>findBacklogById(String id, String username){
    	 projectService.findProjectByIdentifier(id, username);

         return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
	 
	 public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id, String username) {

//		 Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
//		 if(backlog==null) {
//			 throw new ProjectNotFoundException("Project with id '"+backlog_id+"' does not exist");
//		 }
		 projectService.findProjectByIdentifier(backlog_id, username);
		 
		 ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		 if(projectTask == null) {
			 throw new ProjectNotFoundException("Project Task '"+pt_id+"' not found");
		 }
		 
		 if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			 throw new ProjectNotFoundException("Project Task '"+pt_id+"' does not exist in the project"+backlog_id);
		 }
		 return projectTask;
	 }
	 
//	 public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id) {
//		 ProjectTask projectTask = projectTaskRepository.findByProjectSequence(backlog_id);
//		 ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);
	 public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id, String username){
	        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);
	 projectTask = updatedTask;
		 return projectTaskRepository.save(projectTask);
	 }
	 
//	 public void deletePTByProjectSequence(String backlog_id, String pt_id) {
//		 ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);
	 public void deletePTByProjectSequence(String backlog_id, String pt_id, String username){
	        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);
		  projectTaskRepository.delete(projectTask);
	 }

}
