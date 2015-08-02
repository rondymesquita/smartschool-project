package br.com.async.controller;

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
import br.com.async.core.application.CourseApplication;
import br.com.async.core.entities.Course;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

/**
 * Created by rondymesquita on Jul 25, 2015
 *
 */
@Controller
public class CourseController extends BaseController{
	
	private CourseApplication courseApplication = ApplicationContext.getInstance().getBean("courseApplicationImpl", CourseApplication.class);
	
	private static String CONTROLLER = "courses/";
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/courses", method = RequestMethod.GET)
    public String listPage(Model model){
    	model.addAttribute("courses",courseApplication.list());
    	return CONTROLLER + "courses";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/courses/{search}", method = RequestMethod.GET)
    public String searchByCodeOrName(Model model, @PathVariable String search){
    	ResponseData responseData = null;
    	
    	System.out.println(search);
    	List<Course> list = courseApplication.searchByCodeOrName(search);
    	if(list.size() == 0){
    		responseData = new ResponseData(Constants.NO_RESULT, ResponseData.INFO);
    		model.addAttribute(Constants.RESPONSE_DATA_QUERY , responseData);
    	}else
    		model.addAttribute("courses",list);
    	
    	model.addAttribute("search",search);
    	
    	return CONTROLLER + "courses";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/courses/new", method = RequestMethod.GET)
	public String newPage(Model model){
		return CONTROLLER + "coursesNew";
	}
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/courses", method = RequestMethod.POST)
    public String save(@ModelAttribute Course course, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(course);
    	
    	boolean resultQuery = courseApplication.save(course);
    	ResponseData responseData;
    	
    	if(resultQuery)
    		responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
    	else
    		responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
    	return "redirect:/courses";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/courses/edit", method = RequestMethod.POST)
    public String editPage(@ModelAttribute Course course, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(course);
    	
    	Course c = courseApplication.findByCode(course.getCode());
    	
    	model.addAttribute("course",c);
    	
    	return CONTROLLER + "coursesEdit";
    }
    
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/courses/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Course course, Model model, final RedirectAttributes redirectAttributes){
    	System.out.println(course);
    	
    	boolean resultQuery = courseApplication.update(course);
    	ResponseData responseData;
    	
    	if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
    	return "redirect:/course";
    }
    
    @Authenticate
    @RoleManager
    @RequestMapping(value="/courses/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseData delete(@PathVariable String id){
        Course course = new Course();
        course.setCode(Integer.parseInt(id));

        boolean resultQuery = courseApplication.delete(course);
        ResponseData responseData;

        if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

        return responseData;
    }

}
