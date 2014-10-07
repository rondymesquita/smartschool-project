package br.com.async.repository.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextRegister {

	private static ApplicationContext applicationContext;
	
	static{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static <T> T getBean(String bean, Class<T> classType){
		return (T) applicationContext.getBean(bean, classType);
	}
}
