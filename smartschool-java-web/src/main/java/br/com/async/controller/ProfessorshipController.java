package br.com.async.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.async.annotations.Authenticate;
import br.com.async.annotations.RoleManager;
import br.com.async.annotations.RoleProfessor;
import br.com.async.config.ApplicationContext;
import br.com.async.core.application.CourseApplication;
import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.application.SemesterApplication;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Course;
import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Semester;
import br.com.async.core.entities.Student;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

@Controller
public class ProfessorshipController extends BaseController{
	
	private ProfessorshipApplication professorshipApplication = ApplicationContext.getInstance().getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
	private ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
	private DisciplineApplication disciplineApplication = ApplicationContext.getInstance().getBean("disciplineApplicationImpl", DisciplineApplication.class);
	private StudentApplication studentApplication = ApplicationContext.getInstance().getBean("studentApplicationImpl", StudentApplication.class);
	private CourseApplication courseApplication = ApplicationContext.getInstance().getBean("courseApplicationImpl", CourseApplication.class);
	private SemesterApplication semesterApplication = ApplicationContext.getInstance().getBean("semesterApplicationImpl", SemesterApplication.class);
	
	private static String CONTROLLER = "professorships/";
	
	@Authenticate
    @RoleProfessor
	@RequestMapping(value="/professorships", method = RequestMethod.GET)
	public String professorshipsPage(Model model){
		
		model.addAttribute("professorships",professorshipApplication.list());
		return CONTROLLER + "professorships";
	}
	
	@Authenticate
    @RoleProfessor
	@RequestMapping(value="/professorships/new", method = RequestMethod.GET)
	public String professorshipsNew(Model model){
		
		model.addAttribute("professors",professorApplication.list());
		model.addAttribute("disciplines",disciplineApplication.list());
		model.addAttribute("courses",courseApplication.list());
		model.addAttribute("semesters",semesterApplication.list());
		
		
		return CONTROLLER + "professorshipsNew";
	}
	
	@Authenticate
    @RoleProfessor
	@RequestMapping(value="/professorships", method = RequestMethod.POST)
	public String save(Model model, 
			@RequestParam String professorCode,
			@RequestParam String disciplineCode,
			@RequestParam String courseCode,
			@RequestParam String semesterCode,
			@RequestParam String[] studentsCodes,
			HttpServletRequest request, HttpServletResponse response, final RedirectAttributes redirectAttributes){
		
		ResponseData responseData;
		
		Professor professor = professorApplication.findByCode(Integer.parseInt(professorCode));
		Discipline discipline = disciplineApplication.findByCode(Integer.parseInt(disciplineCode));
		Semester semester = semesterApplication.findByCode(Integer.parseInt(semesterCode));
		Course course = courseApplication.findByCode(Integer.parseInt(courseCode));
		
		Set<Student> students = new HashSet<Student>(); 
		for (String code : studentsCodes) {
			Student student = studentApplication.findByCode(Integer.parseInt(code));
			students.add(student);
		}
		Professorship professorship = new Professorship();
		professorship.setDiscipline(discipline);
		professorship.setProfessor(professor);
		professorship.setStudents(students);
		professorship.setSemester(semester);
		professorship.setCourse(course);
		
		
		boolean resultQuery = professorshipApplication.save(professorship);
		
		if(resultQuery)
    		responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
    	else
    		responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);
    	
    	redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
    	return "redirect:/professorships";
				
	}
	
	@Authenticate
    @RoleProfessor
    @RequestMapping(value="/professorships/{search}", method = RequestMethod.GET)
    public String searchByCodeOrDisciplineOrProfessor(Model model, @PathVariable String search) throws UnsupportedEncodingException{
    	ResponseData responseData = null;
    	
    	search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
    	List<Professorship> list = professorshipApplication.searchByCodeOrDisciplineOrProfessor(search);
    	if(list.size() == 0){
    		responseData = new ResponseData(Constants.NO_RESULT, ResponseData.INFO);
    		model.addAttribute(Constants.RESPONSE_DATA_QUERY , responseData);
    	}else
    		model.addAttribute("professorships",list);
    	
    	model.addAttribute("search",search);
    	
    	return CONTROLLER + "professorships";
    }
	
	@Authenticate
    @RoleManager
    @RequestMapping(value="/professorships/delete", method = RequestMethod.POST)
    public String delete(@RequestBody String code, final RedirectAttributes redirectAttributes){
		Professorship professorship = new Professorship();
		professorship.setCode(Integer.parseInt(code.replace("code=","")));

        boolean resultQuery = professorshipApplication.delete(professorship);
        ResponseData responseData;

        if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

        redirectAttributes.addFlashAttribute(Constants.RESPONSE_DATA,responseData);
    	return "redirect:/professorships";
    }
	
	
	
}
