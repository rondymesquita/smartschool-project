package br.com.async.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContext {
	
	public static AnnotationConfigApplicationContext context;
	
	public static AnnotationConfigApplicationContext getInstance(){
		if(context == null){
			context = new AnnotationConfigApplicationContext();
			context.scan("br.com.async.core");
			context.refresh();
			System.out.println("Context Initialized");
		}
		return context;
	}

}
