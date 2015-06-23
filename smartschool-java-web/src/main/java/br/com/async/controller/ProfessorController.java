package br.com.async.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.async.config.ApplicationContext;
import br.com.async.core.application.ProfessorApplication;

@Controller
public class ProfessorController extends BaseController{
	
	private ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
    
    private static String CONTROLLER = "professors/";
    
    @RequestMapping(value="/professors", method = RequestMethod.GET)
    public String professorsPage(Model model){
    	
    	model.addAttribute("professors",professorApplication.list());
    	
    	return CONTROLLER + "professors";
    }
	
	
}
