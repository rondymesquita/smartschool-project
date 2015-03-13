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
import br.com.async.entities.Permission;
import br.com.async.util.Constants;

@Component
public class Interceptor extends HandlerInterceptorAdapter {

	@Autowired
	private HttpSession httpSession;

	private String token = "";
	private String tokenSession = "";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		AuthUser authUser = (AuthUser) httpSession.getAttribute(Constants.USER_KEY);
		if(authUser == null)
			authUser = new AuthUser();
		
		if (method.isAnnotationPresent(Authenticate.class)) {
			
			token = request.getHeader(Constants.AUTH_TOKEN);
			tokenSession = authUser.getAuthToken();
			System.out.println("token:========="+token);
			System.out.println("tokenSession:=="+tokenSession);
			System.out.println(authUser);
			
			if (token == null || tokenSession == null) {
				System.out.println("nulos");
				response.sendRedirect("/smartschool-ws/unauthorized");
				return false;
			} else {
				
				
				if (token.equals(tokenSession)) {
					
					Permission permission = new Permission(method, authUser.getRole());
					if(!PermissionManager.getInstance().hasPermission(permission)){
						response.sendRedirect("/smartschool-ws/unauthorized");
						return false;
					}
					
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
	

}
