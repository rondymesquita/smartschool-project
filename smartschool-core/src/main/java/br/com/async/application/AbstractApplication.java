package br.com.async.application;

import java.io.Serializable;

public interface AbstractApplication <T, E extends Serializable>{
	public boolean save(E entity);

}
