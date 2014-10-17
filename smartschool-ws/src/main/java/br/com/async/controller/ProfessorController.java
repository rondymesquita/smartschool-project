package br.com.async.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.annotations.Authenticate;
import br.com.async.config.ApplicationContext;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Professor;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

@Controller
public class ProfessorController extends BaseController{
	
	Logger logger = Logger.getLogger(ProfessorController.class.getName());
	
	private ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
	
	@Authenticate
	@RequestMapping(value="/api/professors", method = RequestMethod.GET)
	public @ResponseBody List<Professor> list(HttpServletRequest request, HttpServletResponse response){
		return professorApplication.list();
	}
	
	@Authenticate
	@RequestMapping(value="/api/professors/{id}", method = RequestMethod.GET)
	public @ResponseBody Professor find(@PathVariable String id){
		return professorApplication.findByCode(Integer.parseInt(id));
	}
	
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
