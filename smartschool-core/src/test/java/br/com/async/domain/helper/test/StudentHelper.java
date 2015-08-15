package br.com.async.domain.helper.test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.metamodel.domain.Superclass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Student;

public class StudentHelper extends BaseHelper{
	
	private static StudentApplication studentApplication;
	
	protected static void before(){
		config();
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
	}
	
	public static Student createBasic(){
		
		String registry = UUID.randomUUID().toString();
		String name = UUID.randomUUID().toString();
		String cpf = UUID.randomUUID().toString();
		String email = UUID.randomUUID().toString();
		
		Student student = new Student();
		student.setRegistry(registry);
		Person person = PersonHelper.createBasic();
		
		student.setPerson(person);
		return student;
	}
	
	public static Student saveBasic(){
		before();
		Student student = createBasic();
		studentApplication.save(student);
		return studentApplication.findByCode(student.getCode());
	}

	/**
	 * @return
	 */
	public static Set<Student> createBasicList() {
		Set<Student> students = new HashSet<Student>();
		students.add(createBasic());
		return students;
	}

	/**
	 * @return
	 */
	public static Set<Student> saveBasicList() {
		Set<Student> students = new HashSet<Student>();
		students.add(saveBasic());
		return new HashSet<Student>(studentApplication.list());
	}

	
}
