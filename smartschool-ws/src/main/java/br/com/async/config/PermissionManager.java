package br.com.async.config;

import br.com.async.annotations.RoleManager;
import br.com.async.annotations.RoleProfessor;
import br.com.async.core.entities.Role;
import br.com.async.entities.Permission;

public class PermissionManager {
	
	private static PermissionManager permissionManager;
	
	public static PermissionManager getInstance(){
		if(permissionManager == null)
			permissionManager = new PermissionManager();
		
		return permissionManager;
		
	}
	
	
	public static boolean hasPermission(Permission permission){
		
		if(permission.getMethod().isAnnotationPresent(RoleManager.class) && permission.getRole().equals(Role.ROLE_MANAGER))
			return true;
		
		if(permission.getMethod().isAnnotationPresent(RoleProfessor.class) && 
				(permission.getRole().equals(Role.ROLE_PROFESSOR) || permission.getRole().equals(Role.ROLE_MANAGER))) 
			return true;
		
		return false;
	}

}
