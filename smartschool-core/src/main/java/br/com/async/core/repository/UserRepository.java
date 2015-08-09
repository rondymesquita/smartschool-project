package br.com.async.core.repository;

import br.com.async.core.entities.User;


public interface UserRepository extends AbstractRepository<User, Integer>{

	public boolean login(String username, String password);
	public User findByUsernameAndPassword(String username, String password);
	public User findByUsername(String username);
	
}
