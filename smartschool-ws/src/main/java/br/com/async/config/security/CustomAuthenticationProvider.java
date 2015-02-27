package br.com.async.config.security;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.async.config.ApplicationContext;
import br.com.async.controller.AuthenticationController;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private HttpSession httpSession;

	private UserApplication userApplication = ApplicationContext.getInstance()
			.getBean("userApplicationImpl", UserApplication.class);

	private Logger logger = Logger.getLogger(AuthenticationController.class
			.getName());

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        User user = userApplication.findByUsernameAndPassword(username, password);
    	if(user != null){
    		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    	}else{
    		return null;
    	}
	}

	@Override
	public boolean supports(Class<?> authentication) {
//		return authentication.equals(UsernamePasswordAuthenticationToken.class);
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
