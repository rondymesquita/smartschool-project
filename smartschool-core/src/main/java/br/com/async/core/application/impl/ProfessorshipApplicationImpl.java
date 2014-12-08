package br.com.async.core.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.entities.Professorship;
import br.com.async.core.repository.ProfessorshipRepository;


@Service("professorshipApplicationImpl")
@Transactional
public class ProfessorshipApplicationImpl implements ProfessorshipApplication{

	@Autowired
	@Qualifier("professorshipRepositoryImpl")
	private ProfessorshipRepository repository;

	@Override
	public boolean save(Professorship entity) {
		return repository.save(entity);
	}

	@Override
	public boolean update(Professorship entity) {
		return repository.update(entity);
	}

	@Override
	public boolean delete(Professorship entity) {
		return repository.delete(entity);
	}

	@Override
	public Professorship findByCode(Integer code) {
		return repository.findByCode(code);
	}

	@Override
	public List<Professorship> list() {
		return repository.list();
	}
	
}
