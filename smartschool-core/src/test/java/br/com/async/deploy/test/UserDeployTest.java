package br.com.async.deploy.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Role;
import br.com.async.core.entities.User;
import br.com.async.domain.test.BaseDeployTest;

public class UserDeployTest extends BaseDeployTest{
	
	private UserApplication userApplication;
	
	@Before
	public void before(){
		userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);		
	}

	
	@Test
	public void saveUserTest() throws Exception{
		
		User user = new User();
		user.setUsername("admin@admin");
		user.setPassword("admin");
		Person person = new Person();
		person.setEmail("admin@admin");
		person.setCpf("12345678900");
		person.setName("John Doe");
		person.setRole(Role.ROLE_MANAGER);
		user.setPerson(person);
		
		User u = userApplication.findByUsernameAndPassword("admin@admin", "admin");
		if(u==null){
			boolean result = userApplication.save(user);
			Assert.assertEquals(true, result);
			System.out.println("Saved!");
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
