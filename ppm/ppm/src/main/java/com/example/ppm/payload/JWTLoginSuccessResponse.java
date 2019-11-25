package com.example.ppm.payload;

public class JWTLoginSuccessResponse {
	private boolean success;
	private String token;
	public JWTLoginSuccessResponse(boolean success, String token) {
		this.success = success;
		this.token = token;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "JWTLoginSuccessResponse [success=" + success + ", token=" + token + "]";
	}
	

}
