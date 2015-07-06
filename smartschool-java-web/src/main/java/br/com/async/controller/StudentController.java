package br.com.async.controller;

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
import br.com.async.core.application.StudentApplication;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Student;
import br.com.async.core.entities.User;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

@Controller
public class StudentController extends BaseController {

	 private StudentApplication studentApplication = ApplicationContext.getInstance().getBean("studentApplicationImpl", StudentApplication.class);
	 private UserApplication userApplication = ApplicationContext.getInstance().getBean("userApplicationImpl", UserApplication.class);
	    
	    private static String CONTROLLER = "students/";
	    public String CONTROLLER_NAME = "Studentes";
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/students", method = RequestMethod.GET)
	    public String listPage(Model model){
	    	
	    	try{
	    		model.addAttribute("students",studentApplication.list());
	    	}catch(CannotCreateTransactionException e){
	    		System.err.println(e);
	    	}
	    	return CONTROLLER + "students";
	    }
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/students/{search}", method = RequestMethod.GET)
	    public String searchByCodeOrName(Model model, @PathVariable String search){
	    	ResponseData responseData = null;
	    	
	    	System.out.println(search);
	    	List<Student> list = studentApplication.searchByCodeOrName(search);
	    	if(list.size() == 0){
	    		responseData = new ResponseData(Constants.NO_RESULT, ResponseData.INFO);
	    		model.addAttribute(Constants.RESPONSE_DATA_QUERY , responseData);
	    	}else
	    		model.addAttribute("students",list);
	    	
	    	model.addAttribute("search",search);
	    	
	    	return CONTROLLER + "students";
	    }

	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/students/new", method = RequestMethod.GET)
		public String newPage(Model model){
			return CONTROLLER + "studentsNew";
		}
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/students", method = RequestMethod.POST)
	    public String save(@ModelAttribute Student student, Model model, final RedirectAttributes redirectAttributes){
	    	ResponseData responseData = null;
	    	
	    	boolean resultQuery = studentApplication.save(student);
	  
	    	if(resultQuery)
	    		responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		    else
		    	responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
	    	
	    	
	    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
	    	return "redirect:/students";
	    }
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/students/edit", method = RequestMethod.POST)
	    public String editPage(@ModelAttribute Student student, Model model, final RedirectAttributes redirectAttributes){
	    	System.out.println(student);
	    	
	    	Student d = studentApplication.findByCode(student.getCode());
	    	
	    	model.addAttribute("student",d);
	    	
	    	return CONTROLLER + "studentsEdit";
	    }
	    
	    @Authenticate
	    @RoleProfessor
	    @RequestMapping(value="/students/update", method = RequestMethod.POST)
	    public String update(@ModelAttribute Student student, Model model, final RedirectAttributes redirectAttributes){
	    	System.out.println(student);
	    	
	    	boolean resultQuery = studentApplication.update(student);
	    	ResponseData responseData;
	    	
	    	if(resultQuery)
	            responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
	        else
	            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
	    	
	    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
	    	return "redirect:/students";
	    }
	    
	    @Authenticate
	    @RoleManager
	    @RequestMapping(value="/students/{id}", method = RequestMethod.DELETE)
	    public @ResponseBody ResponseData delete(@PathVariable String id){
	        Student student = new Student();
	        student.setCode(Integer.parseInt(id));

	        boolean resultQuery = studentApplication.delete(student);
	        ResponseData responseData;

	        if(resultQuery)
	            responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
	        else
	            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

	        return responseData;
	    }
	
}
