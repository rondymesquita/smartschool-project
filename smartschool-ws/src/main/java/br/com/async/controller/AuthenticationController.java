package br.com.async.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping(value="/must-be-logged")
	public @ResponseBody ResponseData mustBeLogged(){
		return new ResponseData(Constants.MUST_BE_LOGGED, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED+"");
	}
	
	/**
	 * @param {"username":"johndoe","password":"123"}
	 * @return
	 */
	@RequestMapping(value="/api/login", method = RequestMethod.POST)
	public @ResponseBody ResponseData login(@RequestBody AuthUser login, HttpServletRequest request, HttpServletResponse response){
		
		ResponseData responseData;
		
		//TODO remove this block
		if(login.getUsername().equals("johndoe") && login.getPassword().equals("xxx")){
			httpSession.invalidate();
		}
		
		if(login.getUsername().equals("johndoe") && login.getPassword().equals("123")){
			
//			String token = (String) httpSession.getAttribute(Constants.AUTH_TOKEN);
//			if(token != null){
//				responseData = new ResponseData(Constants.ALREADY_LOGGED_IN, HttpStatus.OK.toString());
//				return responseData;
//			}
			
			String token = HttpUtils.generateToken();
			httpSession.setAttribute(Constants.AUTH_TOKEN, token);
			httpSession.setMaxInactiveInterval(1*10);
			responseData = new ResponseData(token, HttpStatus.OK.toString());
		}else{
			responseData = new ResponseData(Constants.INVALID_USER, HttpStatus.BAD_REQUEST+"");
		}
		
		return responseData;
	}
	
	
	private void addUserToCookieSession(HttpServletRequest request){
		
	}
	
	@RequestMapping(value="/api/token/generate", method = RequestMethod.GET)
	public void getAccessToken(HttpServletRequest request, HttpServletResponse response){
		
		
	}
	
}
