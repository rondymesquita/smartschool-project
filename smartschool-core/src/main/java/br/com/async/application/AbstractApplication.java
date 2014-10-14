package br.com.async.application;

import java.io.Serializable;

import br.com.async.entities.AbstractEntity;

public interface AbstractApplication <E extends AbstractEntity, T extends Serializable>{
	public boolean save(E entity);

}
