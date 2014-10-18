package br.com.async.domain;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;

public class ProfessorTest extends BaseTest{

	private ProfessorApplication professorApplication;

	private AnnotationConfigApplicationContext ctx;

	@Before
	public void before() throws IOException {

		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
		
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
	}

	@Test
	public void saveProfessorTest() throws Exception{
		Professor professor = new Professor();
		professor.setEnrollments("Enrollment");
		professor.setRegistry("123");
		professor.setFormation("msc");
		Person person = new Person();
		person.setName("Rondy");
		person.setCpf("123");
		professor.setPerson(person);
		Assert.assertTrue(professorApplication.save(professor));
	}
	
	@Test
	public void findByCodeProfessorTest() throws Exception{
		
		Professor professor = professorApplication.findByCode(1);
		System.out.println(professor.getPerson().getName());
		Assert.assertNotNull(professor);
	}
	
	@Test
	public void deleteProfessorTest() throws Exception{
		
		Professor professor = new Professor();
		professor.setCode(3);
		
		Assert.assertTrue(professorApplication.delete(professor));
	}
	
	@Test
	public void listProfessorTest() throws Exception{
		List<Professor> list = professorApplication.list();
		for (Professor professor : list) {
			System.out.println(professor.getCode());
		} 
	}
}
