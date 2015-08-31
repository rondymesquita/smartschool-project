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
import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.entities.Discipline;
import br.com.async.util.Constants;
import br.com.async.util.ResponseData;

/**
 * Created by rondymesquita on 11/27/14.
 */
@Controller
public class DisciplineController extends BaseController{

    //private DisciplineApplication disciplineApplication = ApplicationContext.getInstance().getBean("disciplineApplicationImpl", DisciplineApplication.class);
	private DisciplineApplication disciplineApplication = getDisciplineApplication();

    /**
     * @param request
     * @param response
     * @return
     */
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/api/disciplines", method = RequestMethod.GET)
    public @ResponseBody
    List<Discipline> list(){
        return disciplineApplication.list();
    }

    /**
     * @param id
     * @return
     */
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/api/disciplines/{id}", method = RequestMethod.GET)
    public @ResponseBody Discipline find(@PathVariable String id){
        return disciplineApplication.findByCode(Integer.parseInt(id));
    }

    /**
     * @param {"name":"Prog 1","workload":120}
     * @return
     */
    @Authenticate
    @RoleProfessor
    @RequestMapping(value="/api/disciplines", method = RequestMethod.POST)
    public @ResponseBody
    ResponseData save(@RequestBody Discipline discipline){

        boolean resultQuery = disciplineApplication.save(discipline);
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
    @RequestMapping(value="/api/disciplines", method = RequestMethod.PUT)
    public @ResponseBody ResponseData update(@RequestBody Discipline discipline){
        boolean resultQuery = disciplineApplication.update(discipline);
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
    @RequestMapping(value="/api/disciplines/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseData delete(@PathVariable String id){
        Discipline discipline = new Discipline();
        discipline.setCode(Integer.parseInt(id));

        boolean resultQuery = disciplineApplication.delete(discipline);
        ResponseData responseData;

        if(resultQuery)
            responseData = new ResponseData(Constants.REGISTRY_REMOVED, ResponseData.SUCCESS);
        else
            responseData = new ResponseData(Constants.ERROR, ResponseData.ERROR);

        return responseData;
    }
}
