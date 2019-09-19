package com.example.ppm.exceptions;

public class ProjectIdExceptionResponse {
	private String projectIdentifier;

	public ProjectIdExceptionResponse(String projectIdentifier) {
		super();
		this.projectIdentifier = projectIdentifier;
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
	

}
