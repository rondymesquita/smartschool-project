package br.com.async.domain.test;

import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Student;
import br.com.async.domain.helper.test.StudentHelper;

public class StudentTest extends BaseTest{

	private StudentApplication studentApplication;
	
	@Before
	public void before(){
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
		cleanup();
	}
	
	@After
	public void after(){
		cleanup();
	}
	
	@Test
	public void saveStudentTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		Student studentSaved = studentApplication.findByCode(student.getCode());
		Assert.assertEquals(student.toString(), studentSaved.toString());
	}
	
	@Test
	public void updateStudentTest() throws Exception{
		
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
		Student studentToUpdate = studentApplication.findByCode(student.getCode());
		String registry = UUID.randomUUID().toString();
		String name = UUID.randomUUID().toString();
		String cpf = UUID.randomUUID().toString();
		String email = UUID.randomUUID().toString();
		
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
	public void searchByCodeOrNameParamCodeTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
		List<Student> studentSearcheds = studentApplication.searchByCodeOrName(student.getCode().toString());
		Assert.assertNotNull(studentSearcheds);
		Assert.assertEquals(studentSearcheds.get(0).toString(), student.toString());
	}
	
	@Test
	public void searchByCodeOrNameParamNameTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
		List<Student> studentSearcheds = studentApplication.searchByCodeOrName(student.getPerson().getName());
		Assert.assertNotNull(studentSearcheds);
		Assert.assertEquals(studentSearcheds.get(0).toString(), student.toString());
	}
	
	@Test
	public void listStudentTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
		List<Student> list = studentApplication.list();
		for (Student studentSaved : list) {
			Assert.assertNotNull(studentSaved);
			Assert.assertEquals(student.getRegistry(), studentSaved.getRegistry());
			Assert.assertEquals(student.getPerson().getName(), studentSaved.getPerson().getName());
			Assert.assertEquals(student.getPerson().getCpf(), studentSaved.getPerson().getCpf());
			Assert.assertEquals(student.getPerson().getEmail(), studentSaved.getPerson().getEmail());
		} 
	}

	/**
	 * 
	 */
	public void cleanup() {
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
