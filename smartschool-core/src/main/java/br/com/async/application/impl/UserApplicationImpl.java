package br.com.async.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.application.UserApplication;
import br.com.async.entities.User;
import br.com.async.repository.AbstractRepository;


@Service
@Transactional
public class UserApplicationImpl implements UserApplication{
	
	@Autowired
	@Qualifier("userRepository")
	private AbstractRepository<User, Integer> repository;

	@Transactional
	public boolean save(User entity) {
		return repository.save(entity);
	}

}
