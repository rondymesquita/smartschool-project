package br.com.async.core.repository;

import java.util.List;

import br.com.async.core.entities.Semester;

public interface SemesterRepository extends AbstractRepository<Semester, Integer>{
	public List<Semester> searchByCodeOrName(String search);
	
}
