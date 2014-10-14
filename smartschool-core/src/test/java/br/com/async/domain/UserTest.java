package br.com.async.domain;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.application.AbstractApplication;
import br.com.async.application.UserApplication;
import br.com.async.entities.Person;
import br.com.async.entities.User;

public class UserTest {
	
//	@Autowired
//	@Qualifier("userApplication")
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
//		User user = populator.populateBean(User.class);
		
		Person person = new Person("Rondy","123");
        User user = new User(person, "username", "123");
        
		boolean result = userApplication.
				save(user);
		Assert.assertEquals(true, result);
	}

}
