package br.com.async.domain.test;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;

public class BaseTest {
	
	protected static AnnotationConfigApplicationContext ctx;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		ctx = new AnnotationConfigApplicationContext(HibernateConfigTest.class);
		ctx.scan("br.com.async.core");
		
	}
	
	public static void baseCleanup(){
		ProfessorshipTest.cleanup();
		ProfessorTest.cleanup();
		StudentTest.cleanup();
		DisciplineTest.cleanup();
	}
	
	@AfterClass
	public static void afterClass() throws IOException {
		System.out.println("Destroy");
		if(ctx.isRunning()){
			ctx.destroy();
			ctx.close();
		}
	}
	
}
