package br.com.async.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.async.core.entities.Person;
import br.com.async.core.entities.Role;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterBefore(new CustomFilter(), AbstractPreAuthenticatedProcessingFilter.class);
		http.authorizeRequests()
		
			.antMatchers("/settings**").hasRole(Role.MANAGER)
			.antMatchers("/richpayoff**").hasRole(Role.MANAGER)
			.and()
				.formLogin().loginPage("/login").permitAll()
				.loginProcessingUrl("/login")
				.usernameParameter("user")
				.passwordParameter("password")
				.failureUrl("/login-error")
//				.successHandler(new CustomAuthenticationSuccessHandler())
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout=logout")
			.and().csrf().disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("admin").password("123").roles(Role.MANAGER);
	  auth.authenticationProvider(new CustomAuthenticationProvider());
	}
 
}