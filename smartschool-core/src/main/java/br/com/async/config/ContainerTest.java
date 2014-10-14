package br.com.async.config;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.application.UserApplication;
import br.com.async.entities.Person;
import br.com.async.entities.User;


public class ContainerTest {
	
//	@Autowired
//	private static UserApplication userApplication;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		Populator populator = new PopulatorBuilder().build();
		ctx.scan("br.com.async");
		ctx.refresh();
		System.out.println(ctx);
        UserApplication userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
//        User user = populator.populateBean(User.class);
        Person person = new Person("Rondy","123");
        User user = new User(person, "username", "123");
		boolean result = userApplication.save(user);
		if(result)
			System.out.println("Done!");
		else
			System.out.println("Ops!");
	}
}
