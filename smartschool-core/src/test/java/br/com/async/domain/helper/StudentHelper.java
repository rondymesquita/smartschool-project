package br.com.async.domain.helper;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Student;

public class StudentHelper{
	
	private static StudentApplication studentApplication;
	private static AnnotationConfigApplicationContext ctx;
	
	private static void before(){
		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
	}
	
	public static Student createBasic(){
		
		Student student = new Student();
		student.setRegistry("123");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		student.setPerson(person);
		return student;
	}
	
	public static Student saveBasic(){
		before();
		
		Student student = new Student();
		student.setRegistry("123");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		student.setPerson(person);
		studentApplication.save(student);
		return studentApplication.findByCode(student.getCode());
	}

	
}
