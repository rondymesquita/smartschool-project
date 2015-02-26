package br.com.async.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import br.com.async.core.entities.Role;
import br.com.async.entities.HTTPMethod;
import br.com.async.entities.Permission;

public class PermissionManager {
	
	private static PermissionManager permissionManager;
	
	private List<Permission> permissionList = new ArrayList<Permission>();
	
	
	private List<Permission> getPermissionList() {
		permissionList.add(new Permission("/api/disciplines",HTTPMethod.GET, Role.MANAGER));
		return permissionList;
	}
	
	
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public static PermissionManager getInstance(){
		if(permissionManager == null)
			permissionManager = new PermissionManager();
		
		return permissionManager;
		
	}
	
	
	public static boolean checkPermission(){
		
		List<Permission> list = permissionManager.getPermissionList();
		for (Permission permission : list) {
			
		}
		return true;
	}

}
