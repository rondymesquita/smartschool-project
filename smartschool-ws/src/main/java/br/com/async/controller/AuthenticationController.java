package br.com.async.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.application.UserApplication;
import br.com.async.business.UserBusiness;
import br.com.async.entities.AuthUser;
import br.com.async.util.Constants;
import br.com.async.util.HttpUtils;
import br.com.async.util.ResponseData;

@Controller
public class AuthenticationController extends BaseController{
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	@Qualifier("userApplication")
	private UserApplication userApplication;
	
	@RequestMapping(value="/must-be-logged")
	public @ResponseBody ResponseData mustBeLogged(){
		return new ResponseData(Constants.MUST_BE_LOGGED, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED+"");
	}
	
	/**
	 * @param {"username":"johndoe","password":"123"}
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value="/api/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> login(@RequestBody AuthUser login, HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		
		ResponseData responseData;
		
		boolean result = userApplication.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		if(result){
			String token = HttpUtils.generateToken();
			httpSession.setAttribute(Constants.AUTH_TOKEN, token);
			httpSession.setMaxInactiveInterval(10*60);
			return new ResponseEntity<String>("{\"Token\":\""+token+"\",\"Login\":\"TRUE\"}", HttpStatus.OK);
		}else{
			responseData = new ResponseData(Constants.INVALID_USER, HttpStatus.BAD_REQUEST+"");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(responseData);
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
