package br.com.async.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.annotations.Authenticate;
import br.com.async.config.ApplicationContext;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Student;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

@Controller
public class StudentController extends BaseController{
	
	private StudentApplication studentApplication = ApplicationContext.getInstance().getBean("studentApplicationImpl", StudentApplication.class);
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@Authenticate
	@RequestMapping(value="/api/students", method = RequestMethod.GET)
	public @ResponseBody List<Student> list(){
		return studentApplication.list();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Authenticate
	@RequestMapping(value="/api/students/{id}", method = RequestMethod.GET)
	public @ResponseBody Student find(@PathVariable String id){
		return studentApplication.findByCode(Integer.parseInt(id));
	}
	
	/**
	 * @param student
	 * @return
	 */
	@Authenticate
	@RequestMapping(value="/api/students", method = RequestMethod.POST)
	public @ResponseBody ResponseData save(@RequestBody Student student){
		
		boolean resultQuery = studentApplication.save(student);
		ResponseData responseData;
		
		if(resultQuery)
			responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		else
			responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		
		return responseData;
		
	}
	
	/**
	 * @param student
	 * @return
	 */
	@Authenticate
	@RequestMapping(value="/api/students", method = RequestMethod.PUT)
	public @ResponseBody ResponseData update(@RequestBody Student student){
		boolean resultQuery = studentApplication.update(student);
		ResponseData responseData;
		
		if(resultQuery)
			responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
		else
			responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		
		return responseData;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Authenticate
	@RequestMapping(value="/api/students/{id}", method = RequestMethod.DELETE)
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
