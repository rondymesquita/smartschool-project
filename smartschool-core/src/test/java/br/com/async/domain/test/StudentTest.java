package br.com.async.domain.test;

import java.util.List;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Role;
import br.com.async.core.entities.Student;
import br.com.async.domain.helper.test.StudentHelper;

public class StudentTest extends BaseTest{

	private static StudentApplication studentApplication;
	
	@BeforeClass
	public static void before(){
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
	}
	
	@AfterClass
	public static void after(){
		cleanup();
		
	}
	
	@Test
	public void saveStudentTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		Student studentSaved = studentApplication.findByCode(student.getCode());
		Assert.assertEquals(student.getRegistry(), studentSaved.getRegistry());
		Assert.assertEquals(student.getPerson().getName(), studentSaved.getPerson().getName());
		Assert.assertEquals(student.getPerson().getCpf(), studentSaved.getPerson().getCpf());
		Assert.assertEquals(student.getPerson().getEmail(), studentSaved.getPerson().getEmail());
	}
	
	@Test
	public void updateStudentTest() throws Exception{
		
		String registry = UUID.randomUUID().toString();
		String name = UUID.randomUUID().toString();
		String cpf = UUID.randomUUID().toString();
		String email = UUID.randomUUID().toString();
		
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
		Student studentToUpdate = studentApplication.findByCode(student.getCode());
		studentToUpdate.setRegistry(registry);
		studentToUpdate.getPerson().setName(name);
		studentToUpdate.getPerson().setCpf(cpf);
		studentToUpdate.getPerson().setEmail(email);
		
		Assert.assertTrue(studentApplication.update(studentToUpdate));
		
		Student studentUpdated = studentApplication.findByCode(student.getCode());
		Assert.assertEquals(studentUpdated.getRegistry(), registry);
		Assert.assertEquals(studentUpdated.getPerson().getName(), name);
		Assert.assertEquals(studentUpdated.getPerson().getCpf(), cpf);
		Assert.assertEquals(studentUpdated.getPerson().getEmail(), email);
	}
	
	@Test
	public void findByCodeStudentTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
		Student studentSearched = studentApplication.findByCode(student.getCode());
		Assert.assertNotNull(studentSearched);
	}
	
	@Test
	public void listStudentTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
		List<Student> list = studentApplication.list();
		for (Student s : list) {
			Assert.assertNotNull(s);
		} 
	}

	/**
	 * 
	 */
	public static void cleanup() {
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
		List<Student> list = studentApplication.list();
		if(list != null){
			for (Student student : list) {
				student.setPerson(null);
				studentApplication.delete(student);
			}
		}
		
	}
	
}
