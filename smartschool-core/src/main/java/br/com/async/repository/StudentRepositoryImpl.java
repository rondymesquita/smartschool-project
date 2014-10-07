package br.com.async.repository;

import br.com.async.domain.college.Student;

public class StudentRepositoryImpl extends
		AbstractRepositoryImpl<Student, Integer> {

	public StudentRepositoryImpl() {
		super(Student.class);
		// TODO Auto-generated constructor stub
	}

}
