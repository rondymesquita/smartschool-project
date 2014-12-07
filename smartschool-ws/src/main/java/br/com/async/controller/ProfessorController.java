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
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Professor;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

@Controller
public class ProfessorController extends BaseController{
	
	private ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
	
	/**
	 * @param Header Token
	 * @param response
	 * @return [{"code":1,"person":{"code":1,"name":"Name","cpf":"123"},"enrollments":"Enrollment","registry":"123","formation":"msc"}]
	 */
	@Authenticate
	@RequestMapping(value="/api/professors", method = RequestMethod.GET)
	public @ResponseBody List<Professor> list(){
		return professorApplication.list();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Authenticate
	@RequestMapping(value="/api/professors/{id}", method = RequestMethod.GET)
	public @ResponseBody Professor find(@PathVariable String id){
		return professorApplication.findByCode(Integer.parseInt(id));
	}
	
	/**
	 * @param {"person":{"name":"Name","cpf":"123"},"enrollments":"Enrollment","registry":"123","formation":"msc"}
	 * @return {"message":"Salvo!","status":"sucess"}
	 */
	@Authenticate
	@RequestMapping(value="/api/professors", method = RequestMethod.POST)
	public @ResponseBody ResponseData save(@RequestBody Professor professor){
		
		boolean resultQuery = professorApplication.save(professor);
		ResponseData responseData;
		
		if(resultQuery)
			responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		else
			responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		
		return responseData;
		
	}
	
	/**
	 * @param {"code":2,"person":{"code":1,"name":"Rondy","cpf":"123"},"enrollments":"xxx","registry":"123","formation":"msc"}
	 * @return
	 */
	@Authenticate
	@RequestMapping(value="/api/professors", method = RequestMethod.PUT)
	public @ResponseBody ResponseData update(@RequestBody Professor professor){
		boolean resultQuery = professorApplication.update(professor);
		ResponseData responseData;
		
		if(resultQuery)
			responseData = new ResponseData(Constants.REGISTRY_UPDATED, ResponseData.SUCCESS);
		else
			responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		
		return responseData;
	}
	
	/**
	 * @param path id
	 * @return
	 */
	@Authenticate
	@RequestMapping(value="/api/professors/{id}", method = RequestMethod.DELETE)
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
