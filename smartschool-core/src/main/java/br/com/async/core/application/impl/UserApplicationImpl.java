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
		
		boolean resultQuery = repository.findByUsernameAndPassword(username, password);
		
		if(resultQuery)
			return true;
		
		return false;
	}

	@Override
	public boolean update(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByCode(Integer code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Integer> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
