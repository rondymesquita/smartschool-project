package br.com.async.controller;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.annotations.Authenticate;
import br.com.async.annotations.RoleManager;
import br.com.async.annotations.RoleProfessor;
import br.com.async.config.ApplicationContext;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Student;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

@Controller
public class ProfessorshipController extends BaseController{

private ProfessorshipApplication professorshipApplication = ApplicationContext.getInstance().getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@Authenticate
	@RoleManager
	@RequestMapping(value="/api/professorships", method = RequestMethod.GET)
	public @ResponseBody List<Professorship> list(){
		return professorshipApplication.list();
	}
	
	/**
	 * @param id
	 * @return
	 */
	@Authenticate
	@RoleManager
	@RequestMapping(value="/api/professorships/{id}", method = RequestMethod.GET)
	public @ResponseBody Professorship find(@PathVariable String id){
		return professorshipApplication.findByCode(Integer.parseInt(id));
	}
	
	/**
	 * @param {"person":{"name":"Name","cpf":"123"},"registry":"123"}
	 * @return
	 */
	@Authenticate
	@RoleManager
	@RequestMapping(value="/api/professorships", method = RequestMethod.POST)
	public @ResponseBody ResponseData save(@RequestBody Professorship professorship){
		
		boolean resultQuery = professorshipApplication.save(professorship);
		ResponseData responseData;
		
		if(resultQuery)
			responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
		else
			responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		
		return responseData;
		
	}
	
	/**
	 * @param {"code":3,"person":{"name":"Name","cpf":"123"},"registry":"123"}
	 * @return
	 */
	@Authenticate
	@RoleManager
	@RequestMapping(value="/api/professorships", method = RequestMethod.PUT)
	public @ResponseBody ResponseData update(@RequestBody Professorship professorship){
		boolean resultQuery = professorshipApplication.update(professorship);
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
	@RoleManager
	@RequestMapping(value="/api/professorships/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseData delete(@PathVariable String id){
		Professorship professorship = new Professorship();
		professorship.setCode(Integer.parseInt(id));
		
		boolean resultQuery = professorshipApplication.delete(professorship);
		ResponseData responseData;
		
		if(resultQuery)
			responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
		else
			responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
		
		return responseData;
	}
	
	@Authenticate
	@RoleManager
	@RequestMapping(value="/api/professorships/{id}/students", method = RequestMethod.GET)
	public @ResponseBody Set<Student> findStudents(@PathVariable String id){
		Professorship professorship = professorshipApplication.findByCode(Integer.parseInt(id));
		return professorship.getStudents();
	}
	
	
}
