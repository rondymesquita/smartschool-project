package br.com.async.core.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Professor;
import br.com.async.core.repository.ProfessorRepository;

@Service("professorApplicationImpl")
@Transactional
public class ProfessorApplicationImpl implements ProfessorApplication{

	@Autowired
	@Qualifier("professorRepositoryImpl")
	private ProfessorRepository repository;
	
	@Transactional
	public boolean save(Professor entity) {
		 return repository.save(entity);
	}

	@Transactional
	public boolean update(Professor entity) {
		return repository.update(entity);
	}

	@Transactional
	public boolean delete(Professor entity) {
		return repository.delete(entity);
	}

	@Transactional
	public Professor findByCode(Integer code) {
		return repository.findByCode(code);
	}

	@Transactional
	public List<Professor> list() {
		return repository.list();
	}

}
