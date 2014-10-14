package br.com.async.domain;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.application.UserApplication;
import br.com.async.application.impl.UserApplicationImpl;
import br.com.async.entities.MyUser;

public class UserTest {
	
//	private UserApplication userApplication;
	
	private Populator populator;
	private AnnotationConfigApplicationContext ctx;
	
	@Before
	public void before(){
		populator = new PopulatorBuilder().build();
//		userApplication = new UserApplication();
		
		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async");
		ctx.refresh();
	}
	
	
	@Test
	public void saveUserTest() throws Exception{
		UserApplication userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
		MyUser user = populator.populateBean(MyUser.class);
		System.out.println(user.getUsername());
		userApplication.save(user);
		System.out.println("Done!");
	}

}
