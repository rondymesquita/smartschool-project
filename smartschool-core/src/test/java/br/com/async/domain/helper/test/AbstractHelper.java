package br.com.async.domain.helper.test;

import br.com.async.core.entities.AbstractEntity;

public interface AbstractHelper <T extends AbstractEntity> {
	
	public T createBasic();
	public T saveBasic();

}
