package br.com.async.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.async.config.ApplicationContext;
import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;

@Controller

public class ProfessorshipController extends BaseController{
	
	private ProfessorshipApplication professorshipApplication = ApplicationContext.getInstance().getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
	private ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
	private DisciplineApplication disciplineApplication = ApplicationContext.getInstance().getBean("disciplineApplicationImpl", DisciplineApplication.class);
	
	private static String CONTROLLER = "professorships/";
	

	@RequestMapping(value="/professorships", method = RequestMethod.GET)
	public String professorshipsPage(Model model){
		
		model.addAttribute("professorships",professorshipApplication.list());
		return CONTROLLER + "professorships";
	}
	
	@RequestMapping(value="/professorships/new", method = RequestMethod.GET)
	public String professorshipsNew(Model model){
		
		model.addAttribute("professors",professorApplication.list());
		model.addAttribute("disciplines",disciplineApplication.list());
		
		return CONTROLLER + "professorshipsNew";
	}
	
	@RequestMapping(value="/professorships", method = RequestMethod.POST)
	public String save(Model model, 
			@RequestParam String professorCode,
			@RequestParam String disciplineCode,
			String[] students,
			HttpServletRequest request, HttpServletResponse response){
		
		List<String> studentList = Arrays.asList(students);
		
				
		return CONTROLLER + "professorshipsNew";
	}
	
	
	
}
