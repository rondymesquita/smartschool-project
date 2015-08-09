package br.com.async.core.repository.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
		return false;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(entity);

		criteria.add(Restrictions.eq("username",username));
		criteria.add(Restrictions.eq("password",password));
		return (User)criteria.uniqueResult();
	
	}
	
	@Override
	public User findByUsername(String username) {
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(entity);

		criteria.add(Restrictions.eq("username",username));
		return (User)criteria.uniqueResult();
	
	}
	
}
