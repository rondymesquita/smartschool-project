package br.com.async.core.repository;

import java.util.List;

import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Semester;

public interface ProfessorshipRepository extends AbstractRepository<Professorship, Integer>{

	/**
	 * @param search
	 * @return
	 */
	public List<Professorship> searchByCodeOrDisciplineOrProfessor(String search);

	/**
	 * @param courseId
	 * @return
	 */
	public List<Semester> searchSemesterByCourse(String courseId);

	/**
	 * @param semesterId
	 * @return
	 */
	List<Professorship> searchProfessorshipsBySemester(String semesterId);

}
