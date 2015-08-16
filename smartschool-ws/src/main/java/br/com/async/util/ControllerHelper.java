package br.com.async.util;

import javax.servlet.http.HttpSession;

import br.com.async.config.ApplicationContext;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Professor;
import br.com.async.entities.AuthUser;

/**
 * Created by rondymesquita on 16/08/2015
 *
 */
public class ControllerHelper {

	private static UserApplication userApplication = ApplicationContext.getInstance().getBean("userApplicationImpl", UserApplication.class);
	private static ProfessorApplication professorApplication = ApplicationContext.getInstance().getBean("professorApplicationImpl", ProfessorApplication.class);
	
	/**
	 * @param httpSession
	 */
	public static Professor getProfessorBySessionUser(HttpSession httpSession) {
		AuthUser authUser = (AuthUser) httpSession.getAttribute(Constants.USER_KEY);
    	Professor professor = professorApplication.findByEmail(authUser.getUsername());
    	return professor;
	}

}
