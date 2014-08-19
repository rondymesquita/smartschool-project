package br.com.async.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.async.application.TeacherApplication;
import br.com.async.entities.Teacher;
import br.com.async.util.ResponseData;

@Controller
public class TeacherController {
	
	@Autowired
	private TeacherApplication teacherApplication;
	
	@RequestMapping(value="/api/teachers", method = RequestMethod.GET)
	public @ResponseBody List<Teacher> list(){
		return teacherApplication.list();
	}
	
	@RequestMapping(value="/api/teachers/{id}", method = RequestMethod.GET)
	public @ResponseBody Teacher find(@PathVariable String id){
		return teacherApplication.find(Integer.parseInt(id));
	}
	
	@RequestMapping(value="/api/teachers", method = RequestMethod.POST)
	public @ResponseBody ResponseData save(@RequestBody Teacher teacher){
		teacherApplication.save(teacher);
		System.out.println(teacher.getFormation());
		ResponseData responseData = new ResponseData("Saved!", ResponseData.SUCCESS);
		return responseData;
	}
	
	@RequestMapping(value="/api/teachers/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseData UPDATE(@RequestBody Teacher teacher){
		teacherApplication.save(teacher);
		System.out.println(teacher.getFormation());
		ResponseData responseData = new ResponseData("Saved!", ResponseData.SUCCESS);
		return responseData;
	}
	
	
	
}
