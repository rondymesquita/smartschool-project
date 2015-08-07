package br.com.async.core.application;

import java.util.List;

import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;

public interface ProfessorshipApplication  extends AbstractApplication<Professorship, Integer>{
	
	public List<Professorship> searchByCodeOrDisciplineOrProfessor(String search);
	
}
