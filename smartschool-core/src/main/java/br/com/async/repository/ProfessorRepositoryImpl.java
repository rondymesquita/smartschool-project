package br.com.async.repository;

import br.com.async.domain.college.Professor;

public class ProfessorRepositoryImpl extends
		AbstractRepositoryImpl<Professor, Integer> {

	public ProfessorRepositoryImpl() {
		super(Professor.class);
	}

}
