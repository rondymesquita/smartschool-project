package br.com.async.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.async.annotations.Authenticate;
import br.com.async.annotations.RoleManager;
import br.com.async.annotations.RoleProfessor;

@Controller
public class DashboardController extends BaseController{

	@Authenticate
	@RoleProfessor
	@RequestMapping(value={"/dashboard","/"}, method = RequestMethod.GET)
	public String dashboard(HttpServletRequest request, HttpServletResponse response){
		
		return "dashboard/dashboard";
	}
	
}
