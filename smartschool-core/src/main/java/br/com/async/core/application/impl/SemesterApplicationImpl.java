package br.com.async.core.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.core.application.SemesterApplication;
import br.com.async.core.entities.Semester;
import br.com.async.core.repository.SemesterRepository;

@Service("semesterApplicationImpl")
@Transactional
public class SemesterApplicationImpl implements SemesterApplication{
	
	@Autowired
    @Qualifier("semesterRepositoryImpl")
    private SemesterRepository repository;
	
	
	@Override
	public boolean save(Semester entity) {
		return repository.save(entity);
	}

	@Override
	public boolean update(Semester entity) {
		return repository.update(entity);
	}

	@Override
	public boolean delete(Semester entity) {
		return repository.delete(entity);
	}

	@Override
	public Semester findByCode(Integer code) {
		return repository.findByCode(code);
	}

	@Override
	public List<Semester> list() {
		return repository.list();
	}

	@Override
	public List<Semester> searchByCodeOrName(String search) {
		return repository.searchByCodeOrName(search);
	}
	
}
