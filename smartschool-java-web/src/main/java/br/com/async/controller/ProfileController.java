package br.com.async.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.async.config.ApplicationContext;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.User;
import br.com.async.entities.ChangePasswordUser;
import br.com.async.entities.UserSession;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

/**
 * Created by rondymesquita on Aug 7, 2015
 *
 */
@Controller
public class ProfileController {
	
	@Autowired
	private HttpSession httpSession;
	
	private ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
	private UserApplication userApplication = ApplicationContext.getInstance().getBean("userApplicationImpl", UserApplication.class);
	
    private static String CONTROLLER = "profile/";

    @RequestMapping(value="profile/")
	public String profilePage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
    	UserSession userSession = (UserSession) httpSession.getAttribute(Constants.USER_KEY);
    	User user = userApplication.findByUsername(userSession.getUsername());
    	Professor professor = professorApplication.findByEmail(userSession.getUsername());
		
    	model.addAttribute("user", user);
    	model.addAttribute("professor", professor);
		
		return CONTROLLER + "profile";
		
	}
    
	@RequestMapping(value="profile/change-access")
	public String changeAccess(@RequestBody ChangePasswordUser login, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		ResponseData responseData = null;
		User user = userApplication.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		if(user != null){
			user.setPassword(login.getNewPassword());
			user.setUsername(login.getNewUsername());
			boolean result = userApplication.update(user);
			if(result)
				responseData = new ResponseData(Constants.ACCESS_CHANGED, HttpStatus.OK.toString());
			else
				responseData = new ResponseData(Constants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		model.addAttribute("responseData", responseData);
		return "auth/change-data-page";
		
	}
	
	@RequestMapping(value="profile/change-data")
	public String changeData(@RequestBody Professor professor, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		UserSession userSession = (UserSession) httpSession.getAttribute(Constants.USER_KEY);
		//Professor professorToUpdate = professorApplication.findByEmail(userSession.getUsername());
		
		boolean resultQuery = professorApplication.update(professor);
    	ResponseData responseData;
    	
    	if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
		model.addAttribute("responseData", responseData);
		return "auth/change-data-page";
		
	}
	
}
