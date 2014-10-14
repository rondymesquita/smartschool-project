package br.com.async.application;

import java.io.Serializable;

public interface AbstractApplication <E, T extends Serializable>{
	public boolean save(E entity);

}
