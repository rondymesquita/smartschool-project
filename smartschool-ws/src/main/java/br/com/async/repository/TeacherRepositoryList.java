package br.com.async.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.async.entities.Person;
import br.com.async.entities.Teacher;

@Component
public class TeacherRepositoryList implements AbstractRepository<Teacher>{

	private List<Teacher> list;
	
	public TeacherRepositoryList(){
		list = new ArrayList<Teacher>();
		
		Person person;
		Teacher teacher;
		
		person = new Person(1,"Bruce Wayne", "12345678900");
		teacher = new Teacher(1, person, "abc123", "msc");
		list.add(teacher);
		
		person = new Person(2,"Peter Parker", "12345678900");
		teacher = new Teacher(2, person, "abc123", "msc");
		list.add(teacher);
		
		person = new Person(3,"Tony Stark", "12345678900");
		teacher = new Teacher(3, person, "abc123", "msc");
		list.add(teacher);
		
		person = new Person(4,"Hulk", "12345678900");
		teacher = new Teacher(4, person, "abc123", "msc");
		list.add(teacher);
		
		person = new Person(5,"Clark Kent", "12345678900");
		teacher = new Teacher(5, person, "abc123", "msc");
		list.add(teacher);
	}
	
	@Override
	public Teacher find(Integer code) {
		return list.get(code);
	}

	@Override
	public List<Teacher> list() {
		return list;
	}

	@Override
	public void save(Teacher entity) {
		list.add(entity);
	}

	@Override
	public void update(Teacher entity) {
		list.set(entity.getCode(), entity);
	}

	@Override
	public void delete(Teacher entity) {
		list.remove(entity.getCode());
		
	}

}
