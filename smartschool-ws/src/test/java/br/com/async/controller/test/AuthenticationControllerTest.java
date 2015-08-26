package br.com.async.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.async.config.WebConfig;
import br.com.async.core.entities.Role;
import br.com.async.core.entities.User;
import br.com.async.domain.helper.test.UserHelper;
import br.com.async.entities.AuthUser;
import br.com.async.entities.ChangePasswordUser;
import br.com.async.util.Constants;
import br.com.async.util.JsonUtils;
import br.com.async.util.ResponseData;

/**
 * Created by rondymesquita on 25/08/2015
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class AuthenticationControllerTest  extends BaseControllerTest{

	private MockMvc mockMvc;
	
	@Before
	public void before(){
		UserHelper.cleanup();
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void after(){
		UserHelper.cleanup();
	}
	
	@Test
    public void shouldReturnInvalidUserWhenDoLogin() throws Exception {
		
		User user = UserHelper.saveBasic(Role.ROLE_MANAGER);
		AuthUser authUser = new AuthUser();
		authUser.setUsername(user.getUsername());
		authUser.setPassword(user.getPassword()+"*");
		
		String authUserJson = JsonUtils.toJson(authUser, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.INVALID_USER, HttpStatus.BAD_REQUEST+"");
		String jsonReponse = JsonUtils.toJson(responseData);
		
		mockMvc.perform(
				post("/api/login")
				.content(authUserJson)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(jsonReponse));
	
	}
	
	@Test
    public void shouldSaveUser() throws Exception {
		
		User user = UserHelper.createBasic(Role.ROLE_MANAGER);
		String userJson = JsonUtils.toJson(user, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.USER_SAVED, HttpStatus.OK.toString());
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX).replace("Usuáriosalvocomsucesso","Usuário salvo com sucesso");
		
		mockMvc.perform(
				post("/api/users")
				.content(userJson)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(jsonReponse));
	
	}
	
	@Test
    public void shouldChangePassword() throws Exception {
		
		User user = UserHelper.saveBasic(Role.ROLE_MANAGER);
		ChangePasswordUser changePasswordUser = new ChangePasswordUser();
		changePasswordUser.setUsername(user.getUsername());
		changePasswordUser.setPassword(user.getPassword());
		changePasswordUser.setNewPassword(user.getPassword()+"1");
		String changePasswordUserJson = JsonUtils.toJson(changePasswordUser, JsonUtils.BLANK_STRING_REGEX);
		
		ResponseData responseData = new ResponseData(Constants.PASSWORD_CHANGED, HttpStatus.OK.toString());
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX).replace("Senhaalteradacomsucesso!","Senha alterada com sucesso!");
		
		mockMvc.perform(
				post("/api/change-password")
				.content(changePasswordUserJson)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(jsonReponse));
	
	}
	
	@Test
    public void shouldSayHelloToMe() throws Exception {
		
		ResponseData responseData = new ResponseData(Constants.HELLO, HttpStatus.OK.toString());
		String jsonReponse = JsonUtils.toJson(responseData, JsonUtils.BLANK_STRING_REGEX);
		
		mockMvc.perform(
				get("/api/hello")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(jsonReponse));
	
	}
	
}
