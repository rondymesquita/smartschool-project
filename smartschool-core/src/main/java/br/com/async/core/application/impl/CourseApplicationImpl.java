package br.com.async.core.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.async.core.application.CourseApplication;
import br.com.async.core.entities.Course;
import br.com.async.core.repository.CourseRepository;

@Service("courseApplicationImpl")
@Transactional
public class CourseApplicationImpl  implements CourseApplication{
	
	@Autowired
    @Qualifier("courseRepositoryImpl")
    private CourseRepository repository;
	
	
	@Override
	public boolean save(Course entity) {
		return repository.save(entity);
	}

	@Override
	public boolean update(Course entity) {
		return repository.update(entity);
	}

	@Override
	public boolean delete(Course entity) {
		return repository.delete(entity);
	}

	@Override
	public Course findByCode(Integer code) {
		return repository.findByCode(code);
	}

	@Override
	public List<Course> list() {
		return repository.list();
	}

	@Override
	public List<Course> searchByCodeOrName(String search) {
		return repository.searchByCodeOrName(search);
	}

}
