package br.com.async.core.application.impl;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.entities.Discipline;
import br.com.async.core.repository.DisciplineRepository;
import br.com.async.core.repository.StudentRepository;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rondymesquita on 11/27/14.
 */
@Service("disciplineApplicationImpl")
@Transactional
public class DisciplineApplicationImpl implements DisciplineApplication {

    @Autowired
    @Qualifier("disciplineRepositoryImpl")
    private DisciplineRepository repository;

    @Override
    public boolean save(Discipline entity) {
        return repository.save(entity);
    }

    @Override
    public boolean update(Discipline entity) {
        return repository.update(entity);
    }

    @Override
    public boolean delete(Discipline entity) {
        return repository.delete(entity);
    }

    @Override
    public Discipline findByCode(Integer code) {
        return repository.findByCode(code);
    }

    @Override
    public List<Discipline> list() {
        return repository.list();
    }

	@Override
	public List<Discipline> searchByCodeOrName(String search) {
		 return repository.searchByCodeOrName(search);
	}
}
