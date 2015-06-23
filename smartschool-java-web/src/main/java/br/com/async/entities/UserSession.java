package br.com.async.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;

@Data
public class UserSession implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Getter
	private String username;
	@Getter
	private String password;
	@Getter
	private String authToken;
	@Getter
	private String role;
	
	

}
