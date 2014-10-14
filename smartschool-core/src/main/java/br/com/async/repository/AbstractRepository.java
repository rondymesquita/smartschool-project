package br.com.async.repository;

public interface AbstractRepository<T, E> {

	boolean save(T entity);
	
}
