package br.com.async.repository;

public interface AbstractRepository<E, T> {

	boolean save(E entity);
	
}
