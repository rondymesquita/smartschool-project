package br.com.async.core.repository.impl;

import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Professor;
import br.com.async.core.repository.DisciplineRepository;
import br.com.async.core.repository.ProfessorRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by rondymesquita on 11/27/14.
 */
@Repository
@Resource(name="disciplineRepositoryImpl")
public class DisciplineRepositoryImpl  extends AbstractRepositoryImpl<Discipline, Integer> implements DisciplineRepository {

    public DisciplineRepositoryImpl() {
        super(Discipline.class);
    }
}
