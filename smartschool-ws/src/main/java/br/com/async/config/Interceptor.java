package br.com.async.config;

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
import br.com.async.entities.AuthUser;
import br.com.async.util.Constants;

@Component
public class Interceptor extends HandlerInterceptorAdapter {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private AuthenticationController authenticationController;

	private String token = "";
	private String tokenSession = "";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		AuthUser authUser = (AuthUser) httpSession.getAttribute(Constants.USER_KEY);
		
		if(!PermissionManager.permissionChecker(authUser.getRole(), request.getRequestURI())){
			return false; 
		}
		
		if (method.isAnnotationPresent(Authenticate.class)) {

			token = request.getHeader(Constants.AUTH_TOKEN);

			
			tokenSession = authUser.getAuthToken();
			
			if (token == null || tokenSession == null) {
				System.out.println("nulos");
				response.sendRedirect("/smartschool-ws/unauthorized");
				return false;
			} else {
				if (token.equals(tokenSession)) {
					return true;
				}else{
					response.sendRedirect("/smartschool-ws/unauthorized");
					return false;
				}
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
	
	static class PermissionManager{
		
		public static boolean permissionChecker(String role, String route){
			return true;
		}
		
	}

}
