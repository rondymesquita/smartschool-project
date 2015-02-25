package br.com.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.async.config.security.SecurityConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"br.com.async","br.com.async.core"})
@Import({ SecurityConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor());
    }
	
	@Bean
	public Interceptor getInterceptor(){
		return new Interceptor();
	}
	
	
	
}
