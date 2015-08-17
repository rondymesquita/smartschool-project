package br.com.async.domain.helper.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;

/**
 * Created by rondymesquita on 14/08/2015
 *
 */
public class BaseHelper {
	
	protected static AnnotationConfigApplicationContext ctx;
	private static boolean contextWasRefreshed = false;
	
	protected static void config(){
		if(ctx == null){
			ctx = new AnnotationConfigApplicationContext(HibernateConfigTest.class);
			ctx.scan("br.com.async.core");
			refreshContext();
		}
		
	}
	
	private static void refreshContext(){
		
		if(!contextWasRefreshed){
			//ctx.refresh();
			contextWasRefreshed = true;
		}
		
		System.out.println("=============="+contextWasRefreshed);
			
	}
	
}
