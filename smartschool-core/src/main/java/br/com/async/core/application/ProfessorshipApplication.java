package br.com.async.core.application;

import java.util.List;

import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Semester;

public interface ProfessorshipApplication  extends AbstractApplication<Professorship, Integer>{
	
	public List<Professorship> searchByCodeOrDisciplineOrProfessor(String search);
	public List<Semester> searchSemesterByCourse(String courseId);
	public List<Professorship> searchProfessorshipsBySemester(String semesterId);
	public List<Professorship> searchProfessorshipsByProfessorName(String professorName);
	
}
