package br.com.async.domain.helper.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
//		Set<Student> list = new HashSet<Student>();
//		list.add(saveBasic());
		
		saveBasic();
		List<Student> list = studentApplication.list();
		Collections.sort(list, new Comparator<Student>(){

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getPerson().getName().compareTo(o1.getPerson().getName());
			}
			
		});
		
		return new HashSet<Student>(list);
	}

	
}
