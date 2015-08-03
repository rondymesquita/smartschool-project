package br.com.async.entities;

import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Permission {
	
	@Getter
	private Method method;
	
	@Getter
	private String role;
	
	
	
	
	
	
	

}
