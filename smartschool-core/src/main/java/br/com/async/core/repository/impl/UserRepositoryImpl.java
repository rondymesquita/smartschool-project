package br.com.async.core.repository.impl;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import br.com.async.core.entities.User;
import br.com.async.core.repository.UserRepository;

@Repository
@Resource(name="userRepositoryImpl")
public class UserRepositoryImpl extends AbstractRepositoryImpl<User, Integer> implements UserRepository{

	public UserRepositoryImpl(){
		super(User.class);
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByUsernameAndPassword(String username, String password) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		hibernateTemplate.findByCriteria(criteria);
		return true;
	}
	
}
