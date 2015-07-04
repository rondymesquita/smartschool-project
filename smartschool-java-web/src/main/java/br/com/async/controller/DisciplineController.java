package br.com.async.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.async.annotations.Authenticate;
import br.com.async.annotations.RoleManager;
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
    	
    	try{
    		model.addAttribute("disciplines",disciplineApplication.list());
    	}catch(CannotCreateTransactionException e){
    		System.err.println(e);
    	}
    	return CONTROLLER + "disciplines";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/disciplines/{search}", method = RequestMethod.GET)
    public String searchByCodeOrName(Model model, @PathVariable String search){
    	ResponseData responseData = null;
    	
    	System.out.println(search);
    	List<Discipline> list = disciplineApplication.searchByCodeOrName(search);
    	if(list.size() == 0){
    		responseData = new ResponseData(Constants.NO_RESULT, ResponseData.INFO);
    		model.addAttribute(Constants.RESPONSE_DATA_QUERY , responseData);
    	}else
    		model.addAttribute("disciplines",list);
    	
    	model.addAttribute("search",search);
    	
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
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/disciplines/edit", method = RequestMethod.POST)
    public String editDisciplinePage(@ModelAttribute Discipline discipline, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(discipline);
    	
    	Discipline d = disciplineApplication.findByCode(discipline.getCode());
    	
    	model.addAttribute("discipline",d);
    	
    	return CONTROLLER + "disciplinesEdit";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/disciplines/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Discipline discipline, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(discipline);
    	
    	boolean resultQuery = disciplineApplication.update(discipline);
    	ResponseData responseData;
    	
    	if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
    	return "redirect:/disciplines";
    }
    
    @Authenticate
    @RoleManager
    @RequestMapping(value="/disciplines/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseData delete(@PathVariable String id){
        Discipline discipline = new Discipline();
        discipline.setCode(Integer.parseInt(id));

        boolean resultQuery = disciplineApplication.delete(discipline);
        ResponseData responseData;

        if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

        return responseData;
    }
    
   
}
