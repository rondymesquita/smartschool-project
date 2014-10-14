package br.com.async.core.application;

import br.com.async.core.entities.User;

public interface UserApplication extends AbstractApplication<User, Integer>{

	public boolean login(String username, String password);
	public boolean findByUsernameAndPassword(String username, String password);
	
}
