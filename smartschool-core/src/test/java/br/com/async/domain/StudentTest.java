package br.com.async.domain;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Student;

public class StudentTest extends BaseTest{

	private static StudentApplication studentApplication;
	private static AnnotationConfigApplicationContext ctx;
	private static Integer code;
	
	
	@BeforeClass
	public static void before(){
		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
		
		Student student = new Student();
		student.setRegistry("abc");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		student.setPerson(person);
		studentApplication.save(student);
		code = student.getCode();
	}
	
	@AfterClass
	public static void afterClass(){
		List<Student> list = studentApplication.list();
		for (Student student : list) {
			studentApplication.delete(student);
		}
	}
	
	@Test
	public void saveStudentTest() throws Exception{
		Student student = new Student();
		student.setRegistry("abc");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		student.setPerson(person);
		Assert.assertTrue(studentApplication.save(student));
		
	}
	
	@Test
	public void updateStudentTest() throws Exception{
		Student student = new Student();
		student.setRegistry("def");
		Assert.assertTrue(studentApplication.save(student));
		
		Student studentUpdated = studentApplication.findByCode(student.getCode());
		Assert.assertEquals(studentUpdated.getRegistry(), "def");
	}
	
	@Test
	public void findByCodeStudentTest() throws Exception{
		
		Student student = studentApplication.findByCode(code);
		Assert.assertNotNull(student);
	}
	
	@Test
	public void listStudentTest() throws Exception{
		List<Student> list = studentApplication.list();
		for (Student student : list) {
			System.out.println(student.getCode());
		} 
	}
	
}
