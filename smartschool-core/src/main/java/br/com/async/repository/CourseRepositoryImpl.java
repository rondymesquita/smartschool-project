package br.com.async.repository;

import br.com.async.domain.course.Course;

public class CourseRepositoryImpl extends
		AbstractRepositoryImpl<Course, Integer> {

	public CourseRepositoryImpl() {
		super(Course.class);
	}

}
