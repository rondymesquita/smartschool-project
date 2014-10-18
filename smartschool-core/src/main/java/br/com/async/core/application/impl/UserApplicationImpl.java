package br.com.async.core.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.User;
import br.com.async.core.repository.UserRepository;


@Service("userApplicationImpl")
@Transactional
public class UserApplicationImpl implements UserApplication{
	
	@Autowired
	@Qualifier("userRepositoryImpl")
	private UserRepository repository;

	@Transactional
	public boolean save(User entity) {
		return repository.save(entity);
	}

	@Transactional
	public boolean login(String username, String password) {
		return true;
	}

	@Transactional
	public boolean findByUsernameAndPassword(String username, String password) {
		
//		boolean resultQuery = repository.findByUsernameAndPassword(username, password);
//		
//		if(resultQuery)
//			return true;
//		
//		return false;
		
		if(username.equals("admin") && password.equals("123"))
			return true;
		
		return false;
	}

	@Override
	public boolean update(User entity) {
		return repository.update(entity);
	}

	@Override
	public boolean delete(User entity) {
		return repository.delete(entity);
	}

	@Override
	public User findByCode(Integer code) {
		return repository.findByCode(code);
	}

	@Override
	public List<User> list() {
		return repository.list();
	}

}
