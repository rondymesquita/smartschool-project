package br.com.async.domain;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import async.example.MyUser;
import async.example.UserApplicationImpl;

public class UserTest {
	
//	private UserApplication userApplication;
	
	private Populator populator;
	private AnnotationConfigApplicationContext ctx;
	
	@Before
	public void before(){
		populator = new PopulatorBuilder().build();
//		userApplication = new UserApplication();
		
		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("async.example");
		ctx.refresh();
	}
	
	
	@Test
	public void saveUserTest() throws Exception{
		
		
		UserApplicationImpl userApplication = ctx.getBean("userApplication",UserApplicationImpl.class);
		MyUser user = populator.populateBean(MyUser.class);
		System.out.println(user.getUsername());
		userApplication.save(user);
		System.out.println("Done!");
	}

}
