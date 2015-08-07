package br.com.async.core.repository;

import java.util.List;

import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;

public interface ProfessorshipRepository extends AbstractRepository<Professorship, Integer>{

	/**
	 * @param search
	 * @return
	 */
	public List<Professorship> searchByCodeOrDisciplineOrProfessor(String search);

}
