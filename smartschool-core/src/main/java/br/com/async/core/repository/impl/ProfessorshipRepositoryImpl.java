package br.com.async.core.repository.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Professorship;
import br.com.async.core.repository.ProfessorshipRepository;

@Repository
@Resource(name="professorshipRepositoryImpl")
public class ProfessorshipRepositoryImpl  extends AbstractRepositoryImpl<Professorship, Integer> implements ProfessorshipRepository{

	public ProfessorshipRepositoryImpl(){
		super(Professorship.class);
	}
	
}
