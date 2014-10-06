package br.com.async.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.entities.AuthUser;
import br.com.async.util.Constants;
import br.com.async.util.HttpUtils;
import br.com.async.util.ResponseData;

@Controller
public class AuthenticationController {

	@RequestMapping(value="/must-be-logged")
	public void mustBeLogged(){
		ResponseData responseData = new ResponseData(Constants.MUST_BE_LOGGED, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED+"");
	}
	
	/**
	 * @param {"username":"johndoe","password":"123"}
	 * @return
	 */
	@RequestMapping(value="/api/login", method = RequestMethod.POST)
	public @ResponseBody ResponseData login(@RequestBody AuthUser login, HttpServletRequest request, HttpServletResponse response){
		
		ResponseData responseData;
		
		//verify if user is already logged
		Cookie authToken = HttpUtils.getCookieFromRequest(request, Constants.AUTH_TOKEN);
		if(authToken != null){
			responseData = new ResponseData(Constants.ALREADY_LOGGED_IN, HttpStatus.OK.toString());
			return responseData;
		}
		
		if(login.getUsername().equals("johndoe") && login.getPassword().equals("123")){
			String token = UUID.randomUUID().toString();
			response.addCookie(new Cookie(Constants.AUTH_TOKEN, token));
			responseData = new ResponseData(token, HttpStatus.OK.toString());
		}else{
			responseData = new ResponseData(Constants.MUST_BE_LOGGED, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED+"");
		}
		
		return responseData;
	}
	
	private void addUserToCookieSession(HttpServletRequest request){
		
	}
	
	@RequestMapping(value="/api/token/generate", method = RequestMethod.GET)
	public void getAccessToken(HttpServletRequest request, HttpServletResponse response){
		
		
	}
	
}
