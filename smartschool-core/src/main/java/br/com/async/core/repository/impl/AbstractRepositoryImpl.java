package br.com.async.core.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.com.async.core.repository.AbstractRepository;

@Repository
public abstract class AbstractRepositoryImpl<T, E extends Serializable> implements AbstractRepository<T, E> {

	protected final Class<T> entity;

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	public AbstractRepositoryImpl(Class<T> entity) {
		this.entity = entity;
	}

	public Class<T> getEntity() {
		return this.entity;
	}
	
	protected Session getSession(){
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}
	
	public Transaction getTransaction(){
		return hibernateTemplate.getSessionFactory().getCurrentSession().getTransaction();
	}

	@Transactional
	public boolean save(T entity) {
		try {
			hibernateTemplate.save(entity);
			return true;
		} catch (RuntimeException re) {
			System.err.println(re);
			return false;
		}
	}

	@Override
	public boolean update(T entity) {
		try {
			hibernateTemplate.merge(entity);
			return true;
		} catch (RuntimeException re) {
			System.err.println(re);
			return false;
		}
	}

	@Override
	public boolean delete(T entity) {
		
		try {
			hibernateTemplate.delete(entity);
			return true;
		} catch (RuntimeException re) {
			System.err.println(re);
			return false;
		}
		
	}

	public T findByCode(E code) {
		try {
			return hibernateTemplate.get(entity, code);
		} catch (RuntimeException re) {
			System.err.println(re);
			return null;
		}
	}

	public List<T> list() {
		try {
			Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(entity);
			return criteria.list();
		} catch (RuntimeException re) {
			System.err.println(re);
			return null;
		}
	}
}