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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.async.config.WebConfig;
import br.com.async.core.entities.Semester;
import br.com.async.entities.AuthUser;
import br.com.async.helper.test.LoginHelper;
import br.com.async.helper.test.SemesterHelper;
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
public class SemesterControllerTest extends BaseControllerTest{
	
	private MockMvc mockMvc;
	
	@Before
	public void before(){
		SemesterHelper.cleanup();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void after(){
		SemesterHelper.cleanup();
	}
	
	
	@Test
    public void shouldListSemestersTest() throws Exception {
		
		List<Semester> list = new ArrayList<Semester>();
		list.add(SemesterHelper.saveBasic());
		list.add(SemesterHelper.saveBasic());
		list.add(SemesterHelper.saveBasic());
		
		String json = JsonUtils.toJson(list, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/semesters")
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
    public void shouldFindTheSemesterTest() throws Exception {
		
		Semester semester = SemesterHelper.saveBasic();
		String json = JsonUtils.toJson(semester, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/semesters/{id}", semester.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
    public void shouldSaveTheSemesterTest() throws Exception {
		
		Semester semester = SemesterHelper.createBasic();
		String json = JsonUtils.toJson(semester, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				post("/api/semesters")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldUpdateTheSemesterTest() throws Exception {
		
		Semester semester = SemesterHelper.saveBasic();
		Semester semesterToUpdate = SemesterHelper.updateBasic(semester);
		String json = JsonUtils.toJson(semesterToUpdate, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				put("/api/semesters")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldDeleteTheSemesterTest() throws Exception {
		
		Semester semester = SemesterHelper.saveBasic();
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				delete("/api/semesters/{id}", semester.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	
}
