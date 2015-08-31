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
import br.com.async.core.entities.Student;
import br.com.async.entities.AuthUser;
import br.com.async.helper.test.LoginHelper;
import br.com.async.helper.test.StudentHelper;
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
public class StudentControllerTest extends BaseControllerTest{
	
	private MockMvc mockMvc;
	
	@Before
	public void before(){
		StudentHelper.cleanup();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void after(){
		StudentHelper.cleanup();
	}
	
	
	@Test
    public void shouldListStudentsTest() throws Exception {
		
		List<Student> list = new ArrayList<Student>();
		list.add(StudentHelper.saveBasic());
		list.add(StudentHelper.saveBasic());
		list.add(StudentHelper.saveBasic());
		String json = JsonUtils.toJson(list, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/students")
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
    public void shouldFindTheStudentTest() throws Exception {
		
		Student student = StudentHelper.saveBasic();
		String json = JsonUtils.toJson(student, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/students/{id}", student.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session))
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
    public void shouldSaveTheStudentTest() throws Exception {
		
		Student student = StudentHelper.createBasic();
		String json = JsonUtils.toJson(student, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				post("/api/students")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldUpdateTheStudentTest() throws Exception {
		
		Student student = StudentHelper.saveBasic();
		Student studentToUpdate = StudentHelper.updateBasic(student);
		String json = JsonUtils.toJson(studentToUpdate, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				put("/api/students")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldDeleteTheStudentTest() throws Exception {
		
		Student student = StudentHelper.saveBasic();
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				delete("/api/students/{id}", student.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	
}
