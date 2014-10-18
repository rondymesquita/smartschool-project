package br.com.async.core.repository.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Professor;
import br.com.async.core.repository.ProfessorRepository;


@Repository
@Resource(name="professorRepositoryImpl")
public class ProfessorRepositoryImpl extends AbstractRepositoryImpl<Professor, Integer> implements ProfessorRepository{

	public ProfessorRepositoryImpl(){
		super(Professor.class);
	}

	
}
