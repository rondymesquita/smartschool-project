package br.com.async.core.entities;

import org.springframework.security.core.GrantedAuthority;


public class Role implements GrantedAuthority{

	public static final String TEACHER = "TEACHER";
	public static final String MANAGER = "MANAGER";
	public static final String STUDENT = "STUDENT";
	
	public static final String ROLE_TEACHER = "ROLE_TEACHER";
	public static final String ROLE_MANAGER = "ROLE_MANAGER";
	public static final String ROLE_STUDENT = "ROLE_STUDENT";
	
	
	private String authority;
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	
	
	
}
