package br.com.async.repository;

public interface AbstractRepository<E, T> {

	boolean save(E entity);
	void update(E entity);
	void delete(E entity);
	T findByCode(T code);
	
}
