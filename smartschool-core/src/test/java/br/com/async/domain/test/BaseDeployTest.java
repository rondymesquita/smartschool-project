package br.com.async.domain.test;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.config.HibernateConfig;

public class BaseDeployTest {
	
	protected static AnnotationConfigApplicationContext ctx;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);
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
