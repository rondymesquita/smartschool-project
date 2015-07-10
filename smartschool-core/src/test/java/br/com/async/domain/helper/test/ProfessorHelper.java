package br.com.async.domain.helper.test;

import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;

public class ProfessorHelper{
	
	private static ProfessorApplication professorApplication;
	private static AnnotationConfigApplicationContext ctx;
	
	private static void before(){
		ctx = new AnnotationConfigApplicationContext(HibernateConfigTest.class);
		ctx.scan("br.com.async.core");
		ctx.refresh();
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
	}
	
	public static Professor createBasic(){
		
		String registry = UUID.randomUUID().toString();
		String formation = UUID.randomUUID().toString();
		String enrollments = UUID.randomUUID().toString();
		String name = UUID.randomUUID().toString();
		String cpf = UUID.randomUUID().toString();
		String email = UUID.randomUUID().toString();
		
		
		Professor professor = new Professor();
		professor.setRegistry(registry);
		professor.setFormation(formation);
		professor.setEnrollments(enrollments);
		
		//person
		Person person = new Person();
		person.setName(name);
		person.setCpf(cpf);
		person.setEmail(email);
		professor.setPerson(person);
		
		
		return professor;
	}
	
	public static Professor saveBasic(){
		before();
		
		Professor professor = createBasic();
		professorApplication.save(professor);
		return professorApplication.findByCode(professor.getCode());
	}

	
}
