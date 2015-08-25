package br.com.async.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.async.config.WebConfig;
import br.com.async.core.entities.Discipline;
import br.com.async.domain.helper.test.DisciplineHelper;
import br.com.async.entities.AuthUser;
import br.com.async.helper.test.LoginHelper;
import br.com.async.util.Constants;
import br.com.async.util.JsonUtils;
import br.com.async.util.ResponseData;

/**
 * Created by rondymesquita on 18/08/2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class DisciplineControllerTest extends BaseControllerTest{
	
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
    public void shouldListDisciplinesAsManagerTest() throws Exception {
		
		List<Discipline> list = new ArrayList<Discipline>();
		list.add(DisciplineHelper.saveBasic());
		list.add(DisciplineHelper.saveBasic());
		list.add(DisciplineHelper.saveBasic());
		String jsonResult = JsonUtils.toJson(list, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/disciplines")
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonResult));
	}
	
	@Test
    public void shouldFindTheDisciplineAsManagerTest() throws Exception {
		
		Discipline discipline = DisciplineHelper.saveBasic();
		String json = JsonUtils.toJson(discipline, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/disciplines/{id}", discipline.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}

	@Test
    public void shouldSaveTheDisciplineAsManagerTest() throws Exception {
		
		Discipline discipline = DisciplineHelper.createBasic();
		String json = JsonUtils.toJson(discipline, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				post("/api/disciplines")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldUpdateTheDisciplineAsManagerTest() throws Exception {
		
		Discipline discipline = DisciplineHelper.saveBasic();
		Discipline disciplineToUpdate = DisciplineHelper.updateBasic(discipline);
		String json = JsonUtils.toJson(disciplineToUpdate, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				put("/api/disciplines")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}

	@Test
    public void shouldDeleteTheDisciplineAsManagerTest() throws Exception {
		
		Discipline discipline = DisciplineHelper.saveBasic();
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);

		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				delete("/api/disciplines/{id}", discipline.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}

	
//	@Mock
//	private DisciplineApplicationImpl disciplineApplication; 
	
//	@Test
//    public void shouldNotSaveTheDisciplineTest() throws Exception {
//		
//		//DisciplineApplicationImpl disciplineApplication = Mockito.mock(DisciplineApplicationImpl.class);
//		
//		Discipline discipline = DisciplineHelper.createBasic();
//		String json = JsonUtils.toJson(discipline, JsonUtils.BLANK_STRING_REGEX);
//		
//		ResponseData responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
//		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
//		
//		//Mockito.when(disciplineApplication.save(Mockito.any(Discipline.class))).thenReturn(false);
//		DisciplineApplication disciplineApplication = Mockito.spy(ApplicationContext.getInstance().getBean("disciplineApplicationImpl", DisciplineApplication.class));
//		Mockito.doReturn(false).when(disciplineApplication).save(Mockito.any(Discipline.class));
//		
//		
//		mockMvc.perform(post("/api/disciplines").content(json).contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk())
//		.andExpect(content().string(jsonReponse));
//	}
	
}
