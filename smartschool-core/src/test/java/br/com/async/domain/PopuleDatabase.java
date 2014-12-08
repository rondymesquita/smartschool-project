package br.com.async.domain;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.User;

public class PopuleDatabase extends BaseTest{
	
//	private DisciplineApplication disciplineApplication;
//	private ProfessorApplication professorApplication;
//	private UserApplication userApplication;
//	
//
//	private AnnotationConfigApplicationContext ctx;
//
//	@Before
//	public void before() throws IOException {
//
//		ctx = new AnnotationConfigApplicationContext();
//		ctx.scan("br.com.async.core");
//		ctx.refresh();
//		
//		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
//		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
//		userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
//	}
//	
//	@Test
//	public void saveProfessorTest() throws Exception{
//		Professor professor = new Professor();
//		professor.setEnrollments("Enrollment");
//		professor.setRegistry("123");
//		professor.setFormation("msc");
//		Person person = new Person();
//		person.setName("John");
//		person.setCpf("123");
//		professor.setPerson(person);
//		Assert.assertTrue(professorApplication.save(professor));
//	}
//	
//	@Test
//	public void saveUserTest() throws Exception{
//		
//		User user = new User();
//		user.setPassword("123");
//		user.setUsername("admin");
//		Person person = new Person();
//		person.setCpf("123");
//		person.setName("John");
//		user.setPerson(person);
//		
//		boolean result = userApplication.
//				save(user);
//		Assert.assertEquals(true, result);
//	}
//	
//	@Test
//	public void saveDisciplineTest() throws Exception{
//		Discipline discipline = new Discipline();
//		discipline.setName("Prog 1");
//		discipline.setWorkload(120);
//		Assert.assertTrue(disciplineApplication.save(discipline));
//	}


}
