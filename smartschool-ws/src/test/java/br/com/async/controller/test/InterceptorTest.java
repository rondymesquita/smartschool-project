package br.com.async.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.async.config.WebConfig;
import br.com.async.entities.AuthUser;
import br.com.async.helper.test.LoginHelper;
import br.com.async.util.Constants;
import br.com.async.util.JsonUtils;

/**
 * Created by rondymesquita on 25/08/2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class InterceptorTest extends BaseControllerTest{

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void after(){
	}
	
	@Test
    public void shouldReturnUnathorizedWhenNotAuthenticated() throws Exception {
		
		mockMvc.perform(
				get("/api/disciplines")
				)
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/smartschool-ws/unauthorized"));
	}
	
	@Test
    public void shouldReturnUnathorizedWhenTokenIsWrong() throws Exception {
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		
		mockMvc.perform(
				get("/api/disciplines")
				.header(Constants.AUTH_TOKEN, "wrong-token")
				.session(session)
				)
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/smartschool-ws/unauthorized"));
	}
	
	//TODO 
	@Test
    public void shouldReturnUnathorizedWhenUserHasNoPermission() throws Exception {
		
		MvcResult result = LoginHelper.loginAsProfessor(mockMvc);
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		AuthUser authUser = (AuthUser) JsonUtils.toObject(result.getResponse().getContentAsString(), AuthUser.class);
		
		mockMvc.perform(
				delete("/api/disciplines/{id}", 1)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/smartschool-ws/unauthorized"));
	}
	
}
