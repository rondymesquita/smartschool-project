package br.com.async.controller.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.async.config.WebConfig;
import br.com.async.core.application.DisciplineApplication;

/**
 * Created by rondymesquita on 18/08/2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class DisciplineControllerTest extends BaseControllerTest{
	
	private DisciplineApplication disciplineApplication;
	
	@Before
	public void before(){
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
	}
	
	@After
	public void after(){
		//cleanup();
	}
	
	
	@Test
    public void disciplineListTest() throws Exception {
		
	}
	
}
