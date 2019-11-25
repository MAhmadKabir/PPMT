package com.example.ppm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Backlog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer PTSequence = 0;
	private String projectIdentifier;
	//OneToOne with project
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="project_id", nullable= false)
	@JsonIgnore
	private Project project;
	//OneToMany with project tasks
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy ="backlog", orphanRemoval=true)
	private List<ProjectTask> projectTasks = new ArrayList<>(); 
	public Backlog() {		
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the pTSequence
	 */
	public Integer getPTSequence() {
		return PTSequence;
	}

	/**
	 * @param pTSequence the pTSequence to set
	 */
	public void setPTSequence(Integer pTSequence) {
		PTSequence = pTSequence;
	}

	/**
	 * @return the projectIdentifier
	 */
	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	/**
	 * @param projectIdentifier the projectIdentifier to set
	 */
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the projectTasks
	 */
	public List<ProjectTask> getProjectTasks() {
		return projectTasks;
	}

	/**
	 * @param projectTasks the projectTasks to set
	 */
	public void setProjectTasks(List<ProjectTask> projectTasks) {
		this.projectTasks = projectTasks;
	}
	
}
