package br.com.async.domain;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import br.com.async.domain.college.Professor;

public class TeacherTest {

	private Populator populator; 
	private Professor professor;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		populator = new PopulatorBuilder().build();
		professor = populator.populateBean(Professor.class);
	}
	
	@Test
	public void testProfessor(){
		System.out.println(professor.getFormation());
		System.out.println(professor.getPerson().getName());
	}
	
	
}
