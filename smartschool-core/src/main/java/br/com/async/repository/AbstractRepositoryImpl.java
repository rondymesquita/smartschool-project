package br.com.async.repository;

import java.io.Serializable;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

;
public class AbstractRepositoryImpl<T, E extends Serializable> extends
		HibernateDaoSupport implements AbstractRepository<T, E> {

	protected Class<T> entityClass;
	
	public AbstractRepositoryImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(T entity) {
		getHibernateTemplate().persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public T findByCode(E code) {
		return getHibernateTemplate().get(entityClass, code);
	}

}
