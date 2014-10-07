package br.com.async.entities;

import java.io.Serializable;

public class AuthUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String authToken;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	

}
