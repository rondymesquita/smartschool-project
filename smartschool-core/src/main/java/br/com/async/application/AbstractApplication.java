package br.com.async.application;

import java.io.Serializable;

public interface AbstractApplication <T, E extends Serializable> {

	public void create(T entity);
	public void update(T entity);
	public void delete(T entity);
	public T findByCode(E code);
}
