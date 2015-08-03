package br.com.async.core.repository;

import java.util.List;

import br.com.async.core.entities.Course;

public interface CourseRepository extends AbstractRepository<Course, Integer>{
	public List<Course> searchByCodeOrName(String search);
	
}
