package br.com.async.domain.helper;

import br.com.async.core.entities.AbstractEntity;

public interface AbstractHelper <T extends AbstractEntity> {
	
	public T createBasic();
	public T saveBasic();

}
