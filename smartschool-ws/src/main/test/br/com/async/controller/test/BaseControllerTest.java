package br.com.async.controller.test;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.ApplicationContext;
import br.com.async.core.config.HibernateConfigTest;

/**
 * Created by rondymesquita on 18/08/2015
 *
 */
public class BaseControllerTest {

	protected static AnnotationConfigApplicationContext ctx;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		ApplicationContext.setHibernateConfig(HibernateConfigTest.class);
		ctx = ApplicationContext.getInstance();
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
