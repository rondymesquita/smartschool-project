package br.com.async.repository;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.async.entities.Student;

@Component
public class StudentRepositoryList implements AbstractRepository<Student>{

//	private List<Student> list;
//	private Populator populator; 
//	private Student student;
//	
//	public StudentRepositoryList() {
//		populator = new PopulatorBuilder().build();
//		list = new ArrayList<Student>();
//		
//		for (int i = 0; i < 5; i++) {
//			student = populator.populateBean(Student.class);
//			student.setCode(i);
//			list.add(student);
//		}
//	}
//	
//	@Override
//	public Student find(Integer code) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Student> list() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void save(Student entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void update(Student entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Integer code) {
//		// TODO Auto-generated method stub
//		
//	}

}
