package br.com.async.core.application;

import java.io.Serializable;

import br.com.async.core.entities.AbstractEntity;

public interface AbstractApplication <E extends AbstractEntity, T extends Serializable>{
	public boolean save(E entity);
	public boolean update(E entity);
	public boolean delete(E entity);
	public boolean findByCode(T code);
	public Iterable<T> list();

}
