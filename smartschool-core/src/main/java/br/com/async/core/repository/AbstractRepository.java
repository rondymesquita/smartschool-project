package br.com.async.core.repository;

import java.util.List;

import org.hibernate.Transaction;

public interface AbstractRepository<T, E> {

	boolean save(T entity);
	boolean update(T entity);
	boolean delete(T entity);
	T findByCode(E code);
	public List<T> list();
	public Transaction getTransaction();
	
}
