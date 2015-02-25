package br.com.async.controller;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.async.config.ApplicationContext;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.User;
import br.com.async.entities.AuthUser;
import br.com.async.entities.ChangePasswordUser;
import br.com.async.util.Constants;
import br.com.async.util.HttpUtils;
import br.com.async.util.ResponseData;

@Controller
public class AuthenticationController extends BaseController{
	
	@Autowired
	private HttpSession httpSession;
	
	private UserApplication userApplication = ApplicationContext.getInstance().getBean("userApplicationImpl", UserApplication.class);
	
	private Logger logger = Logger.getLogger(AuthenticationController.class.getName());
	
	@RequestMapping(value="/unauthorized")
	public @ResponseBody ResponseData unauthorized() throws Exception{
		throw new UnauthorizedException();
	}
	
	/**
	 * @param {"username":"johndoe","password":"123"}
	 * @return {"Token":"d1721d27-50e2-49fa-bbfa-2f62946a34b8","Login":"TRUE"}
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
//	@RequestMapping(value="/api/login", method = RequestMethod.POST)
//	public @ResponseBody ResponseEntity<String> login(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
//		
//		ResponseData responseData;
//		
//		String username = (String) map.get("username");
//		String password = (String) map.get("password");
//		
//		User user = userApplication.findByUsernameAndPassword(username, password);
//		if(user != null){
//			String token = HttpUtils.generateToken();
//			httpSession.setAttribute(Constants.AUTH_TOKEN, token);
//			httpSession.setMaxInactiveInterval(60*60*24*7); //1 hora * 24 horas * 7 dias = uma semana
//			
//			String tokenSession = (String) httpSession.getAttribute(Constants.AUTH_TOKEN);
//			
//			AuthUser login = new AuthUser();
//			login.setAuthToken(tokenSession);
//			login.setPersonType(user.getPerson().getPersonType());
//			login.setPassword("");
//			
//			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//			String json = ow.writeValueAsString(login);
//			return new ResponseEntity<String>(json, HttpStatus.OK);
//		}else{
//			responseData = new ResponseData(Constants.INVALID_USER, HttpStatus.BAD_REQUEST+"");
//			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//			String json = ow.writeValueAsString(responseData);
//			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
//		}
//		
//	}

	/**
	 * @param { "person" : { "name" : "Nome", "cpf" : "123" }, "username" : "admin", "password" : "123" }
	 * @return {"message":"Usuário salvo com sucesso","status":"200"}
	 * @throws Exception
	 */
	@RequestMapping(value="/api/users", method = RequestMethod.POST)
	public @ResponseBody ResponseData saveUser(@RequestBody User user) throws Exception{
		
		logger.info("Chegou Aqui");
		System.out.println("Chegou!");
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(user);
		
		logger.info(json);
		
		ResponseData responseData = null;
		try{
			boolean resultQuery = userApplication.save(user);
			if(resultQuery){
				responseData = new ResponseData(Constants.USER_SAVED, HttpStatus.OK.toString());
				return responseData;
			}
		}catch(Exception e){
			e.printStackTrace();
			responseData = new ResponseData(Constants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		return responseData;
		
		
	}
	
	@RequestMapping(value="api/change-password")
	public @ResponseBody ResponseData changePassword(@RequestBody ChangePasswordUser login, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ResponseData responseData = null;
		User user = userApplication.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		if(user != null){
			user.setPassword(login.getNewPassword());
			boolean result = userApplication.update(user);
			if(result)
				responseData = new ResponseData(Constants.PASSWORD_CHANGED, HttpStatus.OK.toString());
			else
				responseData = new ResponseData(Constants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}else{
			throw new UnauthorizedException();
		}
		return responseData;
		
		
	}
	
	
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	public class UnauthorizedException extends RuntimeException{
		
	}
	
}
