package br.com.async.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import br.com.async.entities.User;
import br.com.async.repository.impl.AbstractRepositoryImpl;

@Repository
@Resource(name="userRepository")
public class UserRepository extends AbstractRepositoryImpl<User>{
	
	public UserRepository(){
		super(User.class);
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findByCode(Integer code) {
		// TODO Auto-generated method stub
		return null;
	}

}
