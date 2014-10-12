package async.example;

import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContainerTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		Populator populator = new PopulatorBuilder().build();
		ctx.scan("async.example");
		ctx.refresh();
		System.out.println(ctx);
        UserApplication userApplication = ctx.getBean("userApplication", UserApplication.class);
        MyUser user = populator.populateBean(MyUser.class);
		boolean result = userApplication.create(user);
		if(result)
			System.out.println("Done!");
		else
			System.out.println("Ops!");
	}
}
