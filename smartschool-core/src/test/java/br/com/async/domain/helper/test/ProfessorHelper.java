package br.com.async.domain.helper.test;

import java.util.UUID;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;

public class ProfessorHelper extends BaseHelper{
	
	private static ProfessorApplication professorApplication;
	
	private static void before(){
		config();
		professorApplication = 
				ctx
				.getBean("professorApplicationImpl", ProfessorApplication.class);
	}
	
	public static Professor createBasic(){
		
		String registry = UUID.randomUUID().toString();
		String formation = UUID.randomUUID().toString();
		String enrollments = UUID.randomUUID().toString();
		
		Professor professor = new Professor();
		professor.setRegistry(registry);
		professor.setFormation(formation);
		professor.setEnrollments(enrollments);
		
		//person
		Person person = PersonHelper.createBasic();
		professor.setPerson(person);
		
		return professor;
	}
	
	public static Professor saveBasic(){
		before();
		
		Professor professor = createBasic();
		professorApplication.save(professor);
		return professorApplication.findByCode(professor.getCode());
	}
	
	/**
	 * @param professorToUpdate
	 * @return
	 */
	public static Professor updateBasic(Professor professor) {
		String registry = UUID.randomUUID().toString();
		String formation = UUID.randomUUID().toString();
		String enrollments = UUID.randomUUID().toString();
		
		professor.setRegistry(registry);
		professor.setFormation(formation);
		professor.setEnrollments(enrollments);
		
		Person person = PersonHelper.updateBasic(professor.getPerson());
		professor.setPerson(person);
		return professor;
	}

	
}
