package br.com.async.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.async.config.ApplicationContext;
import br.com.async.core.application.UserApplication;

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
	public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest request, HttpServletResponse response){
		
		
		return "redirect:/dashboard";
	}

}
