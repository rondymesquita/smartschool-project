package br.com.async.core.application;

import java.util.List;

import br.com.async.core.entities.Semester;

public interface SemesterApplication extends AbstractApplication<Semester, Integer>{
	public List<Semester> searchByCodeOrName(String search);

}
