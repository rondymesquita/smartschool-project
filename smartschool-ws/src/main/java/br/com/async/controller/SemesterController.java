package br.com.async.controller;

import java.util.List;

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
import br.com.async.core.application.SemesterApplication;
import br.com.async.core.entities.Semester;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

/**
 * Created by rondymesquita on 11/27/14.
 */
@Controller
public class SemesterController extends BaseController{

    private SemesterApplication semesterApplication = ApplicationContext.getInstance().getBean("semesterApplicationImpl", SemesterApplication.class);

    /**
     * @param request
     * @param response
     * @return
     */
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/api/semesters", method = RequestMethod.GET)
    public @ResponseBody
    List<Semester> list(){
        return semesterApplication.list();
    }

    /**
     * @param id
     * @return
     */
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/api/semesters/{id}", method = RequestMethod.GET)
    public @ResponseBody Semester find(@PathVariable String id){
        return semesterApplication.findByCode(Integer.parseInt(id));
    }

    /**
     * @param {"name":"Prog 1","workload":120}
     * @return
     */
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/api/semesters", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData save(@RequestBody Semester semester){

        boolean resultQuery = semesterApplication.save(semester);
        ResponseData responseData;

        if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_SAVED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

        return responseData;

    }

    /**
     * @param {"code",1,"name":"Prog 1","workload":120}
     * @return
     */
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/api/semesters", method = RequestMethod.PUT)
    public @ResponseBody ResponseData update(@RequestBody Semester semester){
        boolean resultQuery = semesterApplication.update(semester);
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
    @RequestMapping(value="/api/semesters/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseData delete(@PathVariable String id){
        Semester semester = new Semester();
        semester.setCode(Integer.parseInt(id));

        boolean resultQuery = semesterApplication.delete(semester);
        ResponseData responseData;

        if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

        return responseData;
    }
}
