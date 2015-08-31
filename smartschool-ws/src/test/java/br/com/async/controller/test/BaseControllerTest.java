package br.com.async.controller.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.codehaus.jackson.JsonProcessingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import br.com.async.config.ApplicationContext;
import br.com.async.entities.AuthUser;
import br.com.async.helper.test.HibernateConfigTest;
import br.com.async.util.JsonUtils;

/**
 * Created by rondymesquita on 18/08/2015
 *
 */
public class BaseControllerTest {

	protected static AnnotationConfigApplicationContext ctx;
	
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		ApplicationContext.setHibernateConfig(HibernateConfigTest.class);
		ctx = ApplicationContext.getInstance();
		ctx.scan("br.com.async.core");
		
	}
	
	@AfterClass
	public static void afterClass() throws IOException {
		System.out.println("Destroy");
//		if(ctx.isRunning()){
//			ctx.destroy();
//			ctx.close();
//		}
	}
	
	
	protected MockHttpSession getSession(MvcResult result){
		return (MockHttpSession) result.getRequest().getSession();
	}
	
	protected AuthUser getAuthUser(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException, IOException{
		return (AuthUser) JsonUtils.toObject(result.getResponse().getContentAsString(), AuthUser.class);
	}
	
	protected String getResponseAsString(MvcResult result) throws UnsupportedEncodingException{
		return result.getResponse().getContentAsString();
	}
	
}
