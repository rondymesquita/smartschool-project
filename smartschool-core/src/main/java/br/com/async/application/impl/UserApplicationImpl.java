package br.com.async.application.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.application.UserApplication;
import br.com.async.entities.User;
import br.com.async.repository.AbstractRepository;


@Service
@Transactional
@Resource(name="userApplication")
@Component
public class UserApplicationImpl implements UserApplication{
	
	@Autowired
	@Qualifier("userRepository")
	private AbstractRepository<User, Integer> repository;

	@Transactional
	public boolean save(User entity) {
		return repository.save(entity);
	}

	@Override
	public boolean login(String username, String password) {
		return true;
	}

	@Override
	public boolean findByUsernameAndPassword(String username, String password) {
		
		if(username.equals("admin") && password.equals("123"))
			return true;
		
		return false;
	}

}
