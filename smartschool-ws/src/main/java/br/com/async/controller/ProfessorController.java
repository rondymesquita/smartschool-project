package br.com.async.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.application.ProfessorApplication;
import br.com.async.domain.college.Professor;
import br.com.async.util.ResponseData;

@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorApplication professorApplication;
	
	@RequestMapping(value="/api/professors", method = RequestMethod.GET)
	public @ResponseBody List<Professor> list(){
		return professorApplication.list();
	}
	
	@RequestMapping(value="/api/professors/{id}", method = RequestMethod.GET)
	public @ResponseBody Professor find(@PathVariable String id){
		return professorApplication.find(Integer.parseInt(id));
	}
	
	@RequestMapping(value="/api/professors", method = RequestMethod.POST)
	public @ResponseBody ResponseData save(@RequestBody Professor professor){
		professorApplication.save(professor);
		System.out.println(professor.getFormation());
		ResponseData responseData = new ResponseData("Saved!", ResponseData.SUCCESS);
		return responseData;
	}
	
	@RequestMapping(value="/api/professors/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseData UPDATE(@RequestBody Professor professor){
		professorApplication.save(professor);
		System.out.println(professor.getFormation());
		ResponseData responseData = new ResponseData("Saved!", ResponseData.SUCCESS);
		return responseData;
	}
	
	
	
}
