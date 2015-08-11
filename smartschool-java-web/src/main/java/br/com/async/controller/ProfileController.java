package br.com.async.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.async.annotations.Authenticate;
import br.com.async.annotations.RoleProfessor;
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
public class ProfileController extends BaseController{
	
	@Autowired
	private HttpSession httpSession;
	
	private ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
	private UserApplication userApplication = ApplicationContext.getInstance().getBean("userApplicationImpl", UserApplication.class);
	
    private static String CONTROLLER = "profile/";
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/profile", method = RequestMethod.GET)
	public String profilePage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
    	UserSession userSession = (UserSession) httpSession.getAttribute(Constants.USER_KEY);
    	User user = userApplication.findByUsername(userSession.getUsername());
    	Professor professor = professorApplication.findByEmail(userSession.getUsername());
		
    	model.addAttribute("user", user);
    	model.addAttribute("professor", professor);
		
		return CONTROLLER + "profile";
		
	}
    
    @Authenticate
    @RoleProfessor
	@RequestMapping(value="/profile/change-access", method = RequestMethod.POST)
	public String changeAccess(@ModelAttribute Professor professor, String password, String newPassword, HttpServletRequest request, HttpServletResponse response, Model model, final RedirectAttributes redirectAttributes) throws Exception{
		
		ResponseData responseData = null;
		System.out.println("Eita");
		
		UserSession userSession = (UserSession) httpSession.getAttribute(Constants.USER_KEY);
		User user = userApplication.findByUsernameAndPassword(userSession.getUsername(), password);
		if(user != null){
	    	user.setPassword(newPassword);
	    	user.setPerson(professor.getPerson());
	    	user.setUsername(professor.getPerson().getEmail());
	    	
	    	boolean resultQuery =  professorApplication.update(professor, user);
			
	    	if(resultQuery)
	            responseData = new ResponseData(Constants.ACCESS_CHANGED, ResponseData.SUCCESS);
	        else
	            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
		}else{
			responseData = new ResponseData(Constants.INVALID_USER, ResponseData.ERROR);
		}
		
		redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA, responseData);
		return "redirect:/profile";
		
	}
	
}
