package com.example.ppm.exceptions;

public class ProjectNotFoundExceptionResponse {
	private String ProjectNotFound;

	
	public ProjectNotFoundExceptionResponse(String projectNotFound) {
		ProjectNotFound = projectNotFound;
	}

	/**
	 * @return the projectNotFound
	 */
	public String getProjectNotFound() {
		return ProjectNotFound;
	}

	/**
	 * @param projectNotFound the projectNotFound to set
	 */
	public void setProjectNotFound(String projectNotFound) {
		ProjectNotFound = projectNotFound;
	}
	

}
