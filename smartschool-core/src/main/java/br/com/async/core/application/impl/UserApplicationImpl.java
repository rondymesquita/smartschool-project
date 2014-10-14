package br.com.async.core.application.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.User;
import br.com.async.core.repository.AbstractRepository;
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
		
		if(username.equals("admin") && password.equals("123"))
			return true;
		
		return false;
	}

}
