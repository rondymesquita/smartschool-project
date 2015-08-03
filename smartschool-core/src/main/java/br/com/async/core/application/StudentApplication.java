package br.com.async.core.application;

import java.util.List;

import br.com.async.core.entities.Student;

public interface StudentApplication extends AbstractApplication<Student, Integer>{
	public List<Student> searchByCodeOrName(String search);
}
