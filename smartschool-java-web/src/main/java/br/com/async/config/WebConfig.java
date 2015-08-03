package br.com.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"br.com.async","br.com.async.core"})
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	// equivalents for <mvc:resources/> tags
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
    }
	
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor());
    }
	
	@Bean
	public Interceptor getInterceptor(){
		return new Interceptor();
	}
	
	@Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping hm = new WebMvcConfigurationSupport().requestMappingHandlerMapping();
		hm.setOrder(-1);
		hm.setUrlPathHelper(getUrlPathHelper());
        return hm;
    }
	
	@Bean 
	public UrlPathHelper getUrlPathHelper(){
		return new UrlPathHelperCustom();
	}
	
	
	
}
