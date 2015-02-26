package br.com.async.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Permission {
	
	@Getter
	private String route;
	
	@Getter
	private String httpMethod;
	
	@Getter
	private String role;
	
	
	
	
	
	
	

}
