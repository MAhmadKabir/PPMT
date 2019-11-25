package com.example.ppm.exceptions;

public class UsernameAlreadyExistsResponse {
	
	private String username;

	public UsernameAlreadyExistsResponse(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	

}
