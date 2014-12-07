package br.com.async.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;

@Data
public class AuthUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Getter
	private String username;
	@Getter
	private String password;
	@Getter
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
