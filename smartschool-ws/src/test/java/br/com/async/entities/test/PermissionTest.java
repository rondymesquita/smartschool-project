package br.com.async.entities.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.async.config.WebConfig;
import br.com.async.controller.test.BaseControllerTest;
import br.com.async.core.entities.Discipline;
import br.com.async.entities.AuthUser;
import br.com.async.entities.Permission;
import br.com.async.helper.test.DisciplineHelper;
import br.com.async.helper.test.LoginHelper;
import br.com.async.util.Constants;
import br.com.async.util.JsonUtils;

/**
 * Created by rondymesquita on 29/08/2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class PermissionTest extends BaseControllerTest{

	private MockMvc mockMvc;
	
	@Before
	public void before(){
		DisciplineHelper.cleanup();
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void after(){
		DisciplineHelper.cleanup();
	}
	
	@Test
	public void compareTest() throws Exception{
		Discipline discipline = DisciplineHelper.saveBasic();
		String json = JsonUtils.toJson(discipline, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		MvcResult mvcResult = mockMvc.perform(
				get("/api/disciplines/{id}", discipline.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json)).andReturn();
		
		HandlerInterceptor[] interceptors = mvcResult.getInterceptors();
		for (HandlerInterceptor handlerInterceptor : interceptors) {
			HandlerMethod handlerMethod = (HandlerMethod) mvcResult.getHandler();
			Method method = handlerMethod.getMethod();
			Permission permission = new Permission(method, authUser.getRole());
			permission.setMethod(method);
			permission.setRole(authUser.getRole());
			
			Assert.assertEquals(permission.toString(), permission.toString());
			Assert.assertEquals(permission.hashCode(), permission.hashCode());
			Assert.assertTrue(permission.equals(permission));
			Assert.assertTrue(!permission.equals(null));
			
		}
	}
	
}
