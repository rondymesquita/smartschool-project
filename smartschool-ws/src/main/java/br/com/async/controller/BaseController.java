package br.com.async.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.util.ResponseData;

public abstract class BaseController {

//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public ResponseData handleException(HttpServletRequest req, Exception ex) {
//	    ResponseData responseData = new ResponseData("Error", HttpStatus.INTERNAL_SERVER_ERROR+"");
//		return responseData;
//	}
	
}
