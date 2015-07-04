package br.com.async.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.async.annotations.Authenticate;
import br.com.async.controller.AuthenticationController;
import br.com.async.entities.UserSession;
import br.com.async.entities.Permission;
import br.com.async.util.Constants;

@Component
public class Interceptor extends HandlerInterceptorAdapter {

	@Autowired
	private HttpSession httpSession;
	
	private final String CONTROLLER_NAME = "CONTROLLER_NAME";
	
	private void controllerParamsConfig(Method method) throws IllegalArgumentException, IllegalAccessException{
			Class c = method.getClass();
			Field heightField;
			try {
				heightField = c.getField(CONTROLLER_NAME);
				 String heightValue = (String) heightField.get(method.getClass().newInstance());
			      System.out.println("Height: " + heightValue.toString());
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
		UserSession userSession = (UserSession) httpSession.getAttribute(Constants.USER_KEY);
		
		if (method.isAnnotationPresent(Authenticate.class)) {
			
			if(userSession != null){
				Permission permission = new Permission(method, userSession.getRole());
				
				if(!PermissionManager.getInstance().hasPermission(permission)){
					response.sendRedirect("/smartschool-java-web/auth/login");
					return false;
				}
			}else{
				response.sendRedirect("/smartschool-java-web/auth/login");
				return false;
			}
		}

		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
	
	
	

}
