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

import br.com.async.core.application.AbstractApplication;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.User;

public class UserTest {
	
	@Autowired
	@Qualifier("userApplicationImpl")
	private UserApplication userApplication;
	
	private Populator populator;
	private AnnotationConfigApplicationContext ctx;
	
	@Before
	public void before(){
		populator = new PopulatorBuilder().build();
		
		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
	}
	
	
	@Test
	public void saveUserTest() throws Exception{
		UserApplication userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
		
		
		User user = new User();
		user.setPassword("123");
		user.setUsername("admin");
		Person person = new Person();
		person.setCpf("123");
		person.setName("Rondy");
		user.setPerson(person);
		
		boolean result = userApplication.
				save(user);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void loginWithUsernameAndPasswordTest(){
		UserApplication userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
        boolean result = userApplication.findByUsernameAndPassword("admin", "123");
		Assert.assertEquals(true, result);
	}
	@Test
	public void loginWithWrongUsernameAndPasswordTest(){
		UserApplication userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
        boolean result = userApplication.findByUsernameAndPassword("admin", "1234");
		Assert.assertEquals(false, result);
	}

}
