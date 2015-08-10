package br.com.async.config.test;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan({ "br.com.async.core" })
public class HibernateConfigTest {

	@Value("${postgres.driverClassName}")
	private String driverClassName;
	
	@Value("${postgres.url.test}")
	private String url;
	
	@Value("${postgres.username}")
	private String username;
	
	@Value("${postgres.password}")
	private String password;
	
	@Value("${postgres.dialect}")
	private String hibernateDialect;
	
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;
	
	@Value("${hibernate.hbm2ddl.auto.test}")
	private String hibernateHbm2ddlAuto;
	
	/*
	 * 
	 */
	@Value("${postgres.url.codeship}")
	private String urlCodeship;
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan(new String[] { "br.com.async.core" });
		return sessionFactory;
	}

	@Bean
	public DataSource getDataSource() {
		Logger logger = Logger.getLogger(HibernateConfigTest.class.getName());
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		
		if(System.getenv("PG_USER") != null){
			logger.log(Level.INFO, "====> Running on Codeship Environment");
			dataSource.setUrl(urlCodeship);
			dataSource.setUsername(System.getenv("PG_USER"));
			dataSource.setPassword(System.getenv("PG_PASSWORD"));
		}else{
			logger.log(Level.INFO, "====> Running on other Environment");
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
		}
		
		
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	@Bean
	@Autowired
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

	public Properties getHibernateProperties() {
		return new Properties() {
			private static final long serialVersionUID = 1L;
			{
				setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
				setProperty("hibernate.dialect", hibernateDialect);
				setProperty("hibernate.show_sql", hibernateShowSql);
				setProperty("hibernate.globally_quoted_identifiers", "true");
			}
		};
	}

}
