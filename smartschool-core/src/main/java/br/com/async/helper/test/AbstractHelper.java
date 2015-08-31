package br.com.async.helper.test;

import br.com.async.core.entities.AbstractEntity;

public interface AbstractHelper <T extends AbstractEntity> {
	
	public T createBasic();
	public T saveBasic();

}
