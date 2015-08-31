package br.com.async.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.async.config.WebConfig;
import br.com.async.controller.DisciplineController;
import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.entities.Discipline;
import br.com.async.entities.AuthUser;
import br.com.async.helper.test.DisciplineHelper;
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
	
	@Mock
	private DisciplineApplication disciplineApplication;
	
	@InjectMocks
	private DisciplineController disciplineController = new DisciplineController();
	
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
	
	@Test
    public void shouldReturnErrorWhenDeleteTheDisciplineAsManagerTest() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(disciplineController).build();
		
		Discipline discipline = DisciplineHelper.saveBasic();
		
		Mockito.when(disciplineApplication.delete(Mockito.any(Discipline.class))).thenReturn(false);
		
		ResponseData responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);

		mockMvc.perform(
				delete("/api/disciplines/{id}", discipline.getCode())
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldReturnErrorWhenUpdateTheDisciplineAsManagerTest() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(disciplineController).build();
		Mockito.when(disciplineApplication.update(Mockito.any(Discipline.class))).thenReturn(false);
		
		Discipline discipline = DisciplineHelper.saveBasic();
		Discipline disciplineToUpdate = DisciplineHelper.updateBasic(discipline);
		String json = JsonUtils.toJson(disciplineToUpdate, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);

		mockMvc.perform(
				put("/api/disciplines")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldReturnErrorWhenSaveTheDisciplineAsManagerTest() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(disciplineController).build();
		Mockito.when(disciplineApplication.save(Mockito.any(Discipline.class))).thenReturn(false);
		
		Discipline discipline = DisciplineHelper.createBasic();
		String json = JsonUtils.toJson(discipline, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		mockMvc.perform(
				post("/api/disciplines")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
}
