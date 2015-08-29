package br.com.async.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.async.config.WebConfig;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Role;
import br.com.async.core.entities.Semester;
import br.com.async.core.entities.User;
import br.com.async.domain.helper.test.ProfessorHelper;
import br.com.async.domain.helper.test.ProfessorshipHelper;
import br.com.async.domain.helper.test.SemesterHelper;
import br.com.async.domain.helper.test.UserHelper;
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
public class ProfessorshipControllerTest extends BaseControllerTest{
	
	private MockMvc mockMvc;
	
	@Before
	public void before(){
		ProfessorshipHelper.cleanup();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void after(){
		ProfessorshipHelper.cleanup();
	}
	
	
	@Test
    public void shouldListProfessorshipsTest() throws Exception {
		
		List<Professorship> list = new ArrayList<Professorship>();
		list.add(ProfessorshipHelper.saveBasic());
		
		String json = JsonUtils.toJson(list, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		 mockMvc.perform(
				get("/api/professorships")
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
		 
//		 String response = getResponseAsString(r);
//		Assert.assertEquals(json, JsonUtils.toObject(response, Professorship.class));
	}
	
	@Test
    public void shouldFindTheProfessorshipTest() throws Exception {
		
		Professorship professorship = ProfessorshipHelper.saveBasic();
		String json = JsonUtils.toJson(professorship, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/professorships/{id}", professorship.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
    public void shouldSaveTheProfessorshipTest() throws Exception {
		
		Professorship professorship = ProfessorshipHelper.createBasic();
		String json = JsonUtils.toJson(professorship, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				post("/api/professorships")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldUpdateTheProfessorshipTest() throws Exception {
		
		Professorship professorship = ProfessorshipHelper.saveBasic();
		Professorship professorshipToUpdate = ProfessorshipHelper.updateBasic(professorship);
		String json = JsonUtils.toJson(professorshipToUpdate, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				put("/api/professorships")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldDeleteTheProfessorshipTest() throws Exception {
		
		Professorship professorship = ProfessorshipHelper.saveBasic();
		
		ResponseData responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				delete("/api/professorships/{id}", professorship.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(jsonReponse));
	}
	
	@Test
    public void shouldFindStudents() throws Exception {
		
		Professorship professorship = ProfessorshipHelper.saveBasic();
		String json = JsonUtils.toJson(professorship.getStudents(), JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/professorships/{id}/students", professorship.getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
    public void shouldSearchProfessorshipsBySemester() throws Exception {
		
		List<Professorship> list = new ArrayList<Professorship>();
		list.add(ProfessorshipHelper.saveBasic());
		list.add(ProfessorshipHelper.saveBasic());
		list.add(ProfessorshipHelper.saveBasic());
		list.remove(list.size()-1);
		list.remove(list.size()-1);
		
		String json = JsonUtils.toJson(list, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = LoginHelper.loginAsManager(mockMvc);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		mockMvc.perform(
				get("/api/professorships/semesters/{id}", list.get(0).getSemester().getCode())
				.header(Constants.AUTH_TOKEN, authUser.getAuthToken())
				.session(session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
    public void shouldSearchProfessorshipsIOwn() throws Exception {
		
		Professor professor = ProfessorHelper.createBasic();
		professor.getPerson().setRole(Role.ROLE_MANAGER);
		
		String password = UUID.randomUUID().toString();
		String username = professor.getPerson().getEmail();
		
		User user = new User();
    	user.setPassword(password);
    	user.setPerson(professor.getPerson());
    	user.setUsername(username);
    	
    	ProfessorHelper.saveBasic(professor, user);
		
		MvcResult result = LoginHelper.loginAsManagerWithUser(mockMvc, user);
		MockHttpSession session = getSession(result);
		AuthUser authUser = getAuthUser(result);
		
		List<Professorship> list = new ArrayList<Professorship>();
		Professorship professorship = ProfessorshipHelper.saveBasicWithProfessor(professor);
		list.add(professorship);
		
		String json = JsonUtils.toJson(list, JsonUtils.BLANK_STRING_REGEX);
		
		mockMvc.perform(
				get("/api/professorships/own", 
						list
						.get(0)
						.getSemester()
						.getCode())
				.header(Constants.AUTH_TOKEN, 
						authUser
						.getAuthToken())
				.session(
						session)
				)
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	
}
