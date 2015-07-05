package br.com.async.core.repository;

import java.util.List;

import br.com.async.core.entities.Professor;

public interface ProfessorRepository extends AbstractRepository<Professor, Integer>{
	public List<Professor> searchByCodeOrName(String search);
}
