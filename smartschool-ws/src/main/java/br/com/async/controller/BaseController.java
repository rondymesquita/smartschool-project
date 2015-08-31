package br.com.async.controller;

import br.com.async.config.ApplicationContext;
import br.com.async.core.application.DisciplineApplication;


public class BaseController {

//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public ResponseData handleException(HttpServletRequest req, Exception ex) {
//	    ResponseData responseData = new ResponseData("Error", HttpStatus.INTERNAL_SERVER_ERROR+"");
//		return responseData;
//	}
	
	protected DisciplineApplication getDisciplineApplication(){
		return ApplicationContext.getInstance().getBean("disciplineApplicationImpl", DisciplineApplication.class);
	}
	
}
