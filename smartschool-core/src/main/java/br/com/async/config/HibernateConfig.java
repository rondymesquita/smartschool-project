package br.com.async.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
@ComponentScan({ "br.com.async" })
public class HibernateConfig {

	private String driverClassName = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost:5432/smartschool?charSet=utf8";
	private String username = "postgres";
	private String password = "123456";

	private String hibernateDialect = "org.hibernate.dialect.PostgreSQLDialect";
	private String hibernateShowSql = "true";
	private String hibernateHbm2ddlAuto = "update";

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	// @Bean
	// public AnnotationSessionFactoryBean getSessionFactory() {
	// AnnotationSessionFactoryBean asfb = new AnnotationSessionFactoryBean();
	// asfb.setDataSource(restDataSource());
	// asfb.setHibernateProperties(getHibernateProperties());
	// asfb.setPackagesToScan(new String[] { "br.com.async.a.example" });
	// return asfb;
	// }
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan(new String[] { "br.com.async" });
		return sessionFactory;
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
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
