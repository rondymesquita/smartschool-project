package br.com.async.core.application;

import java.util.List;

import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.User;

public interface ProfessorApplication extends AbstractApplication<Professor, Integer>{
	public boolean save(Professor entity, User user);
	public List<Professor> searchByCodeOrName(String search);
}
