package br.com.async.repository;

import java.util.List;

public interface AbstractRepository <T> {
	
	T find(Integer code);
	List<T> list();
	void save(T entity);
	void update(T entity);
	void delete(T entity);
}
