package br.com.async.deploy;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Role;
import br.com.async.core.entities.User;

public class UserDeployTest {
	
	private UserApplication userApplication;
	private Populator populator;
	private AnnotationConfigApplicationContext ctx;
	
	@Before
	public void before(){
		populator = new PopulatorBuilder().build();
		
		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
		userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);		
	}

	
	@Test
	public void saveUserTest() throws Exception{
		
		User user = new User();
		user.setUsername("admin@admin");
		user.setPassword("admin");
		Person person = new Person();
		person.setCpf("12345678900");
		person.setName("John Doe");
		person.setRole(Role.ROLE_MANAGER);
		user.setPerson(person);
		
		User u = userApplication.findByUsernameAndPassword("admin@admin", "admin");
		if(u==null){
			boolean result = userApplication.save(user);
			Assert.assertEquals(true, result);
		}
	}
	
	@Test
	public void loginWithUsernameAndPasswordTest(){
        User user = userApplication.findByUsernameAndPassword("admin@admin", "admin");
		Assert.assertNotNull(user);
	}
	@Test
	public void loginWithWrongUsernameAndPasswordTest(){
		User user = userApplication.findByUsernameAndPassword("admin@admin", "123");
		Assert.assertNull(user);
	}

}
