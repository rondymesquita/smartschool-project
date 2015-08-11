package br.com.async.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.config.ApplicationContext;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.User;
import br.com.async.entities.ChangePasswordUser;
import br.com.async.entities.UserSession;
import br.com.async.util.Constants;
import br.com.async.util.HttpUtils;
import br.com.async.util.ResponseData;

@Controller
public class AuthenticationController extends BaseController{
	
	@Autowired
	private HttpSession httpSession;
	
	private UserApplication userApplication = ApplicationContext.getInstance().getBean("userApplicationImpl", UserApplication.class);
	
	private Logger logger = Logger.getLogger(AuthenticationController.class.getName());
	
	
	@RequestMapping(value="/auth/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, HttpServletResponse response){
		return "auth/login";
	}
	
	@RequestMapping(value="/auth/login", method = RequestMethod.POST)
	public String login(String username, String password, HttpServletResponse response, Model model){
		ResponseData responseData;
		User user = userApplication.findByUsernameAndPassword(username, password);
		if(user != null){
			String token = HttpUtils.generateToken();
			
			httpSession.setMaxInactiveInterval(60*60*24*7); //1 hora * 24 horas * 7 dias = uma semana
			UserSession userSession = new UserSession();
			userSession.setAuthToken(token);
			userSession.setUsername(username);
			userSession.setRole(user.getPerson().getRole());
			userSession.setPassword("");
			
			httpSession.setAttribute(Constants.USER_KEY, userSession);
			
			return "redirect:/dashboard";
		}else{
			
			responseData = new ResponseData(Constants.INVALID_USER, HttpStatus.BAD_REQUEST+"");
			model.addAttribute("responseData", responseData);
			return "auth/login";
			
		}
		
	}
	
	@RequestMapping(value="/auth/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response){
		httpSession.removeAttribute(Constants.USER_KEY);
		httpSession.invalidate();
		httpSession.setMaxInactiveInterval(0);
		return "redirect:/auth/login";
	}

}
