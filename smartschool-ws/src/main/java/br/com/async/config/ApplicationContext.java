package br.com.async.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContext {
	
	public static AnnotationConfigApplicationContext context;
	private static Class<?> clazz; 
	
	public static AnnotationConfigApplicationContext getInstance(){
		if(context == null){
			
			if(clazz != null)
				context = new AnnotationConfigApplicationContext(clazz);
			else
				context = new AnnotationConfigApplicationContext();
			
			context.scan("br.com.async.core");
			//context.refresh();
			System.out.println("Context Initialized");
		}
		return context;
	}
	
	public static void setHibernateConfig(Class<?> hibernateConfigTestClass ){
		clazz = hibernateConfigTestClass;
	}

}
