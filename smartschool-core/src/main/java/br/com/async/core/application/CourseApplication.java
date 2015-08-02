package br.com.async.core.application;

import java.util.List;

import br.com.async.core.entities.Course;

public interface CourseApplication extends AbstractApplication<Course, Integer>{
	public List<Course> searchByCodeOrName(String search);

}
