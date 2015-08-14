package br.com.async.core.application.impl;

import java.util.List;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Student;
import br.com.async.core.repository.StudentRepository;

@Service("studentApplicationImpl")
@Transactional
public class StudentApplicationImpl implements StudentApplication{

	@Autowired
	@Qualifier("studentRepositoryImpl")
	private StudentRepository repository;

	@Override
	public boolean save(Student entity) {
		return repository.save(entity);
	}

	@Override
	public boolean update(Student entity) {
		return repository.update(entity);
	}

	@Override
	public boolean delete(Student entity) {
		return repository.delete(entity);
	}

	@Override
	public Student findByCode(Integer code) {
		return repository.findByCode(code);
	}

	@Override
	public List<Student> list() {
		return repository.list();
	}
	
	@Override
	public List<Student> searchByCodeOrName(String search) {
		 return repository.searchByCodeOrName(search);
	}
	
	

}
