package br.com.async.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.async.annotations.Authenticate;
import br.com.async.annotations.RoleProfessor;
import br.com.async.config.ApplicationContext;
import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.entities.Discipline;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

/**
 * Created by rondymesquita on 11/27/14.
 */
@Controller
public class DisciplineController extends BaseController{

    private DisciplineApplication disciplineApplication = ApplicationContext.getInstance().getBean("disciplineApplicationImpl", DisciplineApplication.class);
    
    
    private static String CONTROLLER = "disciplines/";
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/disciplines", method = RequestMethod.GET)
    public String disciplinesPage(Model model){
    	
    	model.addAttribute("disciplines",disciplineApplication.list());
    	
    	return CONTROLLER + "disciplines";
    }

    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/disciplines/new", method = RequestMethod.GET)
	public String professorshipsNew(Model model){
		return CONTROLLER + "disciplinesNew";
	}
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/disciplines", method = RequestMethod.POST)
    public String save(@ModelAttribute Discipline discipline, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(discipline);
    	
    	boolean resultQuery = disciplineApplication.save(discipline);
    	ResponseData responseData;
    	
    	if(resultQuery)
    		responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
    	else
    		responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
    	return "redirect:/disciplines";
    }
    
   
}
