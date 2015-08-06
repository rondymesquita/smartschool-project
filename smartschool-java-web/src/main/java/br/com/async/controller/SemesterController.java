package br.com.async.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.stereotype.Controller;
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
import br.com.async.core.application.SemesterApplication;
import br.com.async.core.entities.Semester;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

@Controller
public class SemesterController extends BaseController {

	private SemesterApplication semesterApplication = ApplicationContext.getInstance().getBean("semesterApplicationImpl", SemesterApplication.class);
	
	private static String CONTROLLER = "semesters/";
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/semesters", method = RequestMethod.GET)
    public String listPage(Model model){
    	model.addAttribute("semesters",semesterApplication.list());
    	return CONTROLLER + "semesters";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/semesters/{search}", method = RequestMethod.GET)
    public String searchByCodeOrName(Model model, @PathVariable String search) throws UnsupportedEncodingException{
    	ResponseData responseData = null;
    	
    	search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
    	List<Semester> list = semesterApplication.searchByCodeOrName(search);
    	if(list.size() == 0){
    		responseData = new ResponseData(Constants.NO_RESULT, ResponseData.INFO);
    		model.addAttribute(Constants.RESPONSE_DATA_QUERY , responseData);
    	}else
    		model.addAttribute("semesters",list);
    	
    	model.addAttribute("search",search);
    	
    	return CONTROLLER + "semesters";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/semesters/new", method = RequestMethod.GET)
	public String newPage(Model model){
		return CONTROLLER + "semestersNew";
	}
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/semesters", method = RequestMethod.POST)
    public String save(@ModelAttribute Semester semester, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(semester);
    	
    	boolean resultQuery = semesterApplication.save(semester);
    	ResponseData responseData;
    	
    	if(resultQuery)
    		responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
    	else
    		responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
    	return "redirect:/semesters";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/semesters/edit", method = RequestMethod.POST)
    public String editPage(@ModelAttribute Semester semester, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(semester);
    	
    	Semester c = semesterApplication.findByCode(semester.getCode());
    	
    	model.addAttribute("semester",c);
    	
    	return CONTROLLER + "semestersEdit";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/semesters/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Semester semester, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(semester);
    	
    	boolean resultQuery = semesterApplication.update(semester);
    	ResponseData responseData;
    	
    	if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
    	return "redirect:/semesters";
    }
    
    @Authenticate
    @RoleManager
    @RequestMapping(value="/semesters/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseData delete(@PathVariable String id){
        Semester semester = new Semester();
        semester.setCode(Integer.parseInt(id));

        boolean resultQuery = semesterApplication.delete(semester);
        ResponseData responseData;

        if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

        return responseData;
    }

	
}
