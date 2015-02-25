package br.com.async.domain;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Professor;

public class ExceptionTest {

	private static ProfessorApplication professorApplication;
	private static AnnotationConfigApplicationContext ctx;
	private static Integer code;

	@BeforeClass
	public static void before() throws IOException {

		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
//	@Test(expected = RuntimeException.class) 
	@Test
	public void findByCodeException() throws Exception{
//		exception.expect(RuntimeException.class);
		Professor professor = professorApplication.findByCode(-1);
//		Assert.assertNull(professor);
		
	}
	
}
