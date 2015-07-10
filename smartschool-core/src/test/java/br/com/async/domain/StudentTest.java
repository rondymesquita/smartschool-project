package br.com.async.domain;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.HibernateConfigTest;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Student;
import br.com.async.domain.helper.StudentHelper;

public class StudentTest extends BaseTest{

	private static StudentApplication studentApplication;
	private static AnnotationConfigApplicationContext ctx;
	private static Integer code;
	
	@BeforeClass
	public static void before(){
		ctx = new AnnotationConfigApplicationContext(HibernateConfigTest.class);
		ctx.scan("br.com.async.core");
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
	}
	
//	@AfterClass
//	public static void afterClass(){
//		List<Student> list = studentApplication.list();
//		for (Student student : list) {
//			studentApplication.delete(student);
//		}
//	}
	
	@Test
	public void saveStudentTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
	}
	
	@Test
	public void updateStudentTest() throws Exception{
		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		
		Student studentUpdated = studentApplication.findByCode(student.getCode());
		studentUpdated.setRegistry("234");
		Assert.assertEquals(studentUpdated.getRegistry(), "234");
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
