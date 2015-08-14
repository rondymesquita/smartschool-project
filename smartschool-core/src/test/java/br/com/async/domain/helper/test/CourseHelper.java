package br.com.async.domain.helper.test;

import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;
import br.com.async.core.application.CourseApplication;
import br.com.async.core.application.SemesterApplication;
import br.com.async.core.entities.Course;

/**
 * Created by rondymesquita on 14/08/2015
 *
 */
public class CourseHelper extends BaseHelper{
	
	private static CourseApplication courseApplication;
	
	private static void before(){
		config();
		courseApplication = ctx.getBean("courseApplicationImpl", CourseApplication.class);
	}
	
	public static Course createBasic() {
		String name = UUID.randomUUID().toString();
		Course course = new Course();
		course.setName(name);
		return course;
	}

	/**
	 * @return
	 */
	public static Course saveBasic() {
		before();
		Course course = createBasic();
		courseApplication.save(course);
		return courseApplication.findByCode(course.getCode());
	}

}
