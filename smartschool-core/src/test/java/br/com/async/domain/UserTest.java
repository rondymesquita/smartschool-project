package br.com.async.domain;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.application.UserApplication;
import br.com.async.entities.User;

public class UserTest {
	
//	private UserApplication userApplication;
	
	private Populator populator;
	private AnnotationConfigApplicationContext ctx;
	
	@Before
	public void before(){
		populator = new PopulatorBuilder().build();
		
		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async");
		ctx.refresh();
	}
	
	
	@Test
	public void saveUserTest() throws Exception{
		UserApplication userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
		User user = populator.populateBean(User.class);
		boolean result = userApplication.save(user);
		Assert.assertEquals(true, result);
	}

}
