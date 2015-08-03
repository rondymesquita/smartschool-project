package br.com.async.domain.helper.test;

import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professorship;

public class ProfessorshipHelper{
	
	private static ProfessorshipApplication professorshipApplication;
	private static AnnotationConfigApplicationContext ctx;
	
	private static void before(){
		ctx = new AnnotationConfigApplicationContext(HibernateConfigTest.class);
		ctx.scan("br.com.async.core");
		ctx.refresh();
		professorshipApplication = ctx.getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
	}
	
	public static Professorship createBasic(){
		
		String registry = UUID.randomUUID().toString();
		String formation = UUID.randomUUID().toString();
		String enrollments = UUID.randomUUID().toString();
		String name = UUID.randomUUID().toString();
		String cpf = UUID.randomUUID().toString();
		String email = UUID.randomUUID().toString();
		
		
		Professorship professorship = new Professorship();
		professorship.setDiary(DiaryHelper.createBasic());
		//professorship.setDiscipline(DisciplineHelper.findBasic());
		//professorship.setProfessor(ProfessorHelper.findBasic());
		//professorship.setStudents(StudentHelper);
		
		//person
		Person person = new Person();
		person.setName(name);
		person.setCpf(cpf);
		person.setEmail(email);
		//professorship.setPerson(person);
		
		
		return professorship;
	}
	
	public static Professorship saveBasic(){
		before();
		
		Professorship professorship = createBasic();
		professorshipApplication.save(professorship);
		return professorshipApplication.findByCode(professorship.getCode());
	}

	
}
