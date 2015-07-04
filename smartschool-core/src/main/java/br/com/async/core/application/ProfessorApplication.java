package br.com.async.core.application;

import br.com.async.core.entities.Professor;
import br.com.async.core.entities.User;

public interface ProfessorApplication extends AbstractApplication<Professor, Integer>{
	public boolean save(Professor entity, User user);
}
