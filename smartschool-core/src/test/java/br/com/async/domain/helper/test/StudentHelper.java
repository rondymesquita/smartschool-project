package br.com.async.domain.helper.test;

import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Student;

public class StudentHelper{
	
	private static StudentApplication studentApplication;
	private static AnnotationConfigApplicationContext ctx;
	
	private static void before(){
		ctx = new AnnotationConfigApplicationContext(HibernateConfigTest.class);
		ctx.scan("br.com.async.core");
		ctx.refresh();
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
	}
	
	public static Student createBasic(){
		
		String registry = UUID.randomUUID().toString();
		String name = UUID.randomUUID().toString();
		String cpf = UUID.randomUUID().toString();
		String email = UUID.randomUUID().toString();
		
		Student student = new Student();
		student.setRegistry(registry);
		Person person = new Person();
		person.setName(name);
		person.setCpf(cpf);
		person.setEmail(email);
		
		student.setPerson(person);
		return student;
	}
	
	public static Student saveBasic(){
		before();
		
		Student student = createBasic();
		studentApplication.save(student);
		return studentApplication.findByCode(student.getCode());
	}

	
}
