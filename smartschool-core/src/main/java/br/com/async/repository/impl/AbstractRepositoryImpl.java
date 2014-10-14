package br.com.async.repository.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.com.async.repository.AbstractRepository;

@Repository
public abstract class AbstractRepositoryImpl<E, T> implements AbstractRepository<E, T>{

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
	public boolean save(E transientInstance) {
		try {
			hibernateTemplate.save(transientInstance);
			return true;
		} catch (RuntimeException re) {
			System.err.println(re);
			return false;
		}
	}

	@Override
	public void update(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T findByCode(T code) {
		// TODO Auto-generated method stub
		return null;
	}
}