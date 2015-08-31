package br.com.async.helper.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.async.core.entities.Role;
import br.com.async.core.entities.User;
import br.com.async.entities.AuthUser;
import br.com.async.util.JsonUtils;

/**
 * Created by rondymesquita on 24/08/2015
 *
 */
public class LoginHelper {
	
	public static MvcResult loginAsManager(MockMvc mockMvc) throws Exception{
		User user = UserHelper.saveBasic(Role.ROLE_MANAGER);
		
		AuthUser authUser = new AuthUser();
		authUser.setUsername(user.getUsername());
		authUser.setPassword(user.getPassword());
		
		String authUserJson = JsonUtils.toJson(authUser, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = mockMvc.perform(post("/api/login").content(authUserJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		return result;
	}
	
	public static MvcResult loginAsManagerWithUser(MockMvc mockMvc, User user) throws Exception{
		
		AuthUser authUser = new AuthUser();
		authUser.setUsername(user.getUsername());
		authUser.setPassword(user.getPassword());
		
		String authUserJson = JsonUtils.toJson(authUser, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = mockMvc.perform(post("/api/login").content(authUserJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		return result;
	}
	
	public static MvcResult loginAsProfessor(MockMvc mockMvc) throws Exception{
		User user = UserHelper.saveBasic(Role.ROLE_PROFESSOR);
		AuthUser authUser = new AuthUser();
		authUser.setUsername(user.getUsername());
		authUser.setPassword(user.getPassword());
		
		String authUserJson = JsonUtils.toJson(authUser, JsonUtils.BLANK_STRING_REGEX);
		
		MvcResult result = mockMvc.perform(post("/api/login").content(authUserJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		return result;
	}
}
