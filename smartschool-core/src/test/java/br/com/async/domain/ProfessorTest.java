package br.com.async.domain;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;

public class ProfessorTest {

	private static ProfessorApplication professorApplication;
	private static AnnotationConfigApplicationContext ctx;
	private static Integer code;

	@BeforeClass
	public static void before() throws IOException {

		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
		
		Professor professor = new Professor();
		professor.setEnrollments("Enrollment");
		professor.setRegistry("123");
		professor.setFormation("msc");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		professor.setPerson(person);
		professorApplication.save(professor);
		code = professor.getCode();
	}
	
	@AfterClass
	public static void afterClass(){
		List<Professor> list = professorApplication.list();
		for (Professor professor : list) {
			professorApplication.delete(professor);
		}
	}
	
	@Test
	public void saveProfessorTest() throws Exception{
		Professor professor = new Professor();
		professor.setEnrollments("Enrollment");
		professor.setRegistry("123");
		professor.setFormation("msc");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		professor.setPerson(person);
		Assert.assertTrue(professorApplication.save(professor));
	}
	
	@Test
	public void findByCodeProfessorTest() throws Exception{
		
		Professor professor = professorApplication.findByCode(code);
		System.out.println(professor.getPerson().getName());
		Assert.assertNotNull(professor);
	}
	
	@Test
	public void updateProfessorTest() throws Exception{
		Professor professor = new Professor();
		professor.setCode(code);
		professor.setRegistry("123");
		professor.setFormation("esp");
		Assert.assertTrue(professorApplication.update(professor));
		
		Professor professorUpdated = professorApplication.findByCode(code);
		Assert.assertEquals(professorUpdated.getFormation(), "esp");		
	}
	
	@Test
	public void listProfessorTest() throws Exception{
		List<Professor> list = professorApplication.list();
		for (Professor professor : list) {
			System.out.println(professor.getCode());
		} 
	}
}
