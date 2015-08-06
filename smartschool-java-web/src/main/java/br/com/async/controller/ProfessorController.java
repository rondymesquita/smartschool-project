package br.com.async.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.async.annotations.Authenticate;
import br.com.async.annotations.RoleManager;
import br.com.async.annotations.RoleProfessor;
import br.com.async.config.ApplicationContext;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.User;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

@Controller
public class ProfessorController extends BaseController{
	
	 	private ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
	    
	    private static String CONTROLLER = "professors/";
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/professors", method = RequestMethod.GET)
	    public String listPage(Model model){
	    	
	    	try{
	    		model.addAttribute("professors",professorApplication.list());
	    	}catch(CannotCreateTransactionException e){
	    		System.err.println(e);
	    	}
	    	return CONTROLLER + "professors";
	    }
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/professors/{search}", method = RequestMethod.GET)
	    public String searchByCodeOrName(Model model, @PathVariable String search) throws UnsupportedEncodingException{
	    	ResponseData responseData = null;
	    	
	    	search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
	    	List<Professor> list = professorApplication.searchByCodeOrName(search);
	    	if(list.size() == 0){
	    		responseData = new ResponseData(Constants.NO_RESULT, ResponseData.INFO);
	    		model.addAttribute(Constants.RESPONSE_DATA_QUERY , responseData);
	    	}else
	    		model.addAttribute("professors",list);
	    	
	    	model.addAttribute("search",search);
	    	
	    	return CONTROLLER + "professors";
	    }

	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/professors/new", method = RequestMethod.GET)
		public String newPage(Model model){
			return CONTROLLER + "professorsNew";
		}
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/professors", method = RequestMethod.POST)
	    public String save(@ModelAttribute Professor professor, @RequestParam("password") String password, Model model, final RedirectAttributes redirectAttributes){
	    	ResponseData responseData = null;
	    	System.out.println(professor);
	    	
	    	User user = new User();
	    	user.setPassword(password);
	    	user.setPerson(professor.getPerson());
	    	user.setUsername(professor.getPerson().getEmail());
	    	
	    	boolean resultQuery = professorApplication.save(professor, user);
	  
	    	if(resultQuery)
	    		responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		    else
		    	responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
	    	
	    	
	    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
	    	return "redirect:/professors";
	    }
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/professors/edit", method = RequestMethod.POST)
	    public String editPage(@ModelAttribute Professor professor, Model model, final RedirectAttributes redirectAttributes){
	    	System.out.println(professor);
	    	
	    	Professor d = professorApplication.findByCode(professor.getCode());
	    	
	    	model.addAttribute("professor",d);
	    	
	    	return CONTROLLER + "professorsEdit";
	    }
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/professors/update", method = RequestMethod.POST)
	    public String update(@ModelAttribute Professor professor, Model model, final RedirectAttributes redirectAttributes){
	    	System.out.println(professor);
	    	
	    	boolean resultQuery = professorApplication.update(professor);
	    	ResponseData responseData;
	    	
	    	if(resultQuery)
	            responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
	        else
	            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
	    	
	    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
	    	return "redirect:/professors";
	    }
	    
	    @Authenticate
	    @RoleManager
	    @RequestMapping(value="/professors/{id}", method = RequestMethod.DELETE)
	    public @ResponseBody ResponseData delete(@PathVariable String id){
	        Professor professor = new Professor();
	        professor.setCode(Integer.parseInt(id));

	        boolean resultQuery = professorApplication.delete(professor);
	        ResponseData responseData;

	        if(resultQuery)
	            responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
	        else
	            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

	        return responseData;
	    }
	
	
}
