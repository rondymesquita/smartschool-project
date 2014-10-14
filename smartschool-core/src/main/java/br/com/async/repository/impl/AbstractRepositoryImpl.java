package br.com.async.repository.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.com.async.entities.MyUser;
import br.com.async.repository.AbstractRepository;

@Repository
public abstract class AbstractRepositoryImpl<E> implements AbstractRepository<MyUser, Integer>{

	private final E entity;
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;

	public AbstractRepositoryImpl(E entity) {
		this.entity = entity;
	}

	@SuppressWarnings("unchecked")
	public AbstractRepositoryImpl(Class<?> classe) {
		this.entity = (E) classe;
	}

	public E getEntity() {
		return this.entity;
	}
	
	@Transactional
	public boolean save(MyUser transientInstance) {
		try {
			hibernateTemplate.save(transientInstance);
			return true;
		} catch (RuntimeException re) {
			System.err.println(re);
			return false;
		}
	}
}