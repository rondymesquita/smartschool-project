package br.com.async.domain.helper.test;

import java.util.List;
import java.util.UUID;

import br.com.async.core.application.CourseApplication;
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

	/**
	 * @param courseToUpdate
	 * @return
	 */
	public static Course updateBasic(Course course) {
		String name = UUID.randomUUID().toString();
		course.setName(name);
		return course;
	}
	
	public static void cleanup(){
		config();
		courseApplication = ctx.getBean("courseApplicationImpl", CourseApplication.class);
		List<Course> list = courseApplication.list();
		if(list != null){
			for (Course course : list) {
				courseApplication.delete(course);
			}
		}
	}

}
