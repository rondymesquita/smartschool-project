package br.com.async.domain.test;

import java.io.IOException;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;
import br.com.async.core.entities.Course;
import br.com.async.core.entities.Diary;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.SchoolClass;
import br.com.async.core.entities.Semester;
import br.com.async.core.entities.Student;
import br.com.async.core.entities.User;

public class BaseTest {
	
	protected static AnnotationConfigApplicationContext ctx;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		ctx = new AnnotationConfigApplicationContext(HibernateConfigTest.class);
		ctx.scan("br.com.async.core");
		
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
