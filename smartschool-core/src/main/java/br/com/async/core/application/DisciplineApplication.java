package br.com.async.core.application;

import java.util.List;

import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Professor;

/**
 * Created by rondymesquita on 11/27/14.
 */
public interface DisciplineApplication extends AbstractApplication<Discipline, Integer>{
	public List<Discipline> searchByCodeOrName(String search);
}
