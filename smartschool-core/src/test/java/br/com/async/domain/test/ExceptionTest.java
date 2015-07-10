package br.com.async.domain.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Professor;

public class ExceptionTest extends BaseTest{

	private static ProfessorApplication professorApplication;

	@BeforeClass
	public static void before() throws IOException {
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test//(expected = RuntimeException.class) 
	public void findByCodeException() throws Exception{
//		exception.expect(RuntimeException.class);
		Professor professor = professorApplication.findByCode(-1);
		Assert.assertNull(professor);
		
	}
	
}
