package br.com.async.core.repository;

import java.util.List;

import br.com.async.core.entities.Student;

public interface StudentRepository extends AbstractRepository<Student, Integer>{
	public List<Student> searchByCodeOrName(String search);
}
