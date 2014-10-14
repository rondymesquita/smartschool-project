package br.com.async.repository;

import br.com.async.entities.User;


public interface UserRepository extends AbstractRepository<User, Integer>{

	public boolean login(String username, String password);
	public boolean findByUsernameAndPassword(String username, String password);
	
}
