package br.com.async.core.application;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Transaction;

import br.com.async.core.entities.AbstractEntity;

public interface AbstractApplication <T extends AbstractEntity, E extends Serializable>{
	public boolean save(T entity);
	public boolean update(T entity);
	public boolean delete(T entity);
	public T findByCode(E code);
	public List<T> list();
}
