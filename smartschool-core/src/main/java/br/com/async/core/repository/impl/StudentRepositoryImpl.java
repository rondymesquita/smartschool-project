package br.com.async.core.repository.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Student;
import br.com.async.core.repository.StudentRepository;

@Repository
@Resource(name="studentRepositoryImpl")
public class StudentRepositoryImpl extends AbstractRepositoryImpl<Student, Integer> implements StudentRepository {

	public StudentRepositoryImpl(){
		super(Student.class);
	}
	
}
