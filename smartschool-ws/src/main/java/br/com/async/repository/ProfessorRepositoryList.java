package br.com.async.repository;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.async.domain.college.Professor;


@Component
public class ProfessorRepositoryList implements AbstractRepository<Professor>{

	private List<Professor> list;
	private Populator populator; 
	private Professor professor;
	
	public ProfessorRepositoryList(){
		populator = new PopulatorBuilder().build();
		list = new ArrayList<Professor>();
		
		for (int i = 0; i < 5; i++) {
			professor = populator.populateBean(Professor.class);
			list.add(professor);
		}
		
	}
	
	@Override
	public Professor find(Integer code) {
		return list.get(code);
	}

	@Override
	public List<Professor> list() {
		return list;
	}

	@Override
	public void save(Professor entity) {
		list.add(entity);
	}

	@Override
	public void update(Professor entity) {
		list.set(entity.getCode(), entity);
	}

	@Override
	public void delete(Professor entity) {
		list.remove(entity.getCode());
		
	}

}
