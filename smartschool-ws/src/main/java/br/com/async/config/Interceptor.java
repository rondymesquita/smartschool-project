package br.com.async.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.async.annotations.Authenticate;
import br.com.async.controller.AuthenticationController;
import br.com.async.util.Constants;

public class Interceptor extends HandlerInterceptorAdapter {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private AuthenticationController authenticationController;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		
		if(method.isAnnotationPresent(Authenticate.class)){
			String token = request.getHeader(Constants.AUTH_TOKEN);
			System.out.println("Token Request : "+ token);
			String tokenSession = (String) httpSession.getAttribute(Constants.AUTH_TOKEN);
			System.out.println("Token Session : "+ token);
			
			if(token != null && tokenSession != null){
				if(token.equals(tokenSession)){
					return true;
				}
			}
		}
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
