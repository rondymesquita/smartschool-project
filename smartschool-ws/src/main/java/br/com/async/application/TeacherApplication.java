package br.com.async.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.async.entities.Teacher;
import br.com.async.repository.TeacherRepositoryList;

@Component
public class TeacherApplication implements AbstractApplication<Teacher>{
	
	@Autowired
	private TeacherRepositoryList teacherRepository;
	
	public Teacher find(Integer id){
		return teacherRepository.find(id);
	}
	
	public List<Teacher> list(){
		return teacherRepository.list();
	}

	@Override
	public void save(Teacher entity) {
		teacherRepository.save(entity);
	}

	@Override
	public void update(Teacher entity) {
		teacherRepository.update(entity);
	}

	@Override
	public void delete(Teacher entity) {
		teacherRepository.delete(entity);
	}

}
