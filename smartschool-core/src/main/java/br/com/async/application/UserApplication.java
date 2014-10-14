package br.com.async.application;

import br.com.async.entities.User;

public interface UserApplication extends AbstractApplication<User, Integer>{

	public boolean login(String username, String password);
	public boolean findByUsernameAndPassword(String username, String password);
	
}
