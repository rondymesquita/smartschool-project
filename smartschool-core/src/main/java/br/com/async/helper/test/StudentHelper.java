package br.com.async.helper.test;

import java.util.ArrayList;
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
	
	public static Student updateBasic(Student student){
		
		String registry = UUID.randomUUID().toString();
		
		student.setRegistry(registry);
		Person person = PersonHelper.updateBasic(student.getPerson());
		
		student.setPerson(person);
		return student;
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
		
		List<Student> list = new ArrayList<Student>();
		list.add(saveBasic());
		Collections.sort(list, new Comparator<Student>(){

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getPerson().getName().compareTo(o1.getPerson().getName());
			}
			
		});
		
		return new HashSet<Student>(list);
	}
	
	public static void cleanup(){
		config();
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
