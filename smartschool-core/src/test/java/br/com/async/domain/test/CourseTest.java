package br.com.async.domain.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.async.core.application.CourseApplication;
import br.com.async.core.entities.Course;
import br.com.async.domain.helper.test.CourseHelper;

@RunWith(MockitoJUnitRunner.class)
public class CourseTest extends BaseTest{

	private CourseApplication courseApplication;

	@Before
	public void before(){
		courseApplication = ctx.getBean("courseApplicationImpl", CourseApplication.class);
		cleanup();
	}
	
	@After
	public void after(){
		cleanup();
	}
	

	@Test
	public void saveSemesterTest() throws Exception{
		Course course = CourseHelper.createBasic();
		Assert.assertTrue(courseApplication.save(course));
		
		Course courseSaved = courseApplication.findByCode(course.getCode());
		Assert.assertEquals(course.toString(), courseSaved.toString());
		
	}
	
	@Test
	public void updateSemesterTest() throws Exception{
		Course course = CourseHelper.createBasic();
		Assert.assertTrue(courseApplication.save(course));
		
		Course courseToUpdate = courseApplication.findByCode(course.getCode());
		courseToUpdate = CourseHelper.updateBasic(courseToUpdate);
		Assert.assertTrue(courseApplication.update(courseToUpdate));
		
		Course courseUpdated = courseApplication.findByCode(courseToUpdate.getCode());
		Assert.assertEquals(courseToUpdate.toString(), courseUpdated.toString());
		
	}
	
	
	@Test
	public void listSemesterTest() throws Exception{
		Course course = CourseHelper.createBasic();
		Assert.assertTrue(courseApplication.save(course));
		
		List<Course> list = courseApplication.list();
		for (Course c : list) {
			Assert.assertNotNull(c);
			Assert.assertEquals(course.toString(), c.toString());
		} 
	}

	@Test
	public void searchByCodeOrNameWithParamCode() throws Exception{
		Course course = CourseHelper.createBasic();
		Assert.assertTrue(courseApplication.save(course));
		
		List<Course> courseSearched = courseApplication.searchByCodeOrName(course.getCode().toString());
		Assert.assertEquals(course.toString(), courseSearched.get(0).toString());  
	}


	@Test
	public void searchByCodeOrNameWithParamName() throws Exception{
		Course course = CourseHelper.createBasic();
		Assert.assertTrue(courseApplication.save(course));
		
		List<Course> courseSearched = courseApplication.searchByCodeOrName(course.getName());
		Assert.assertEquals(course.toString(), courseSearched.get(0).toString());  
	}
	
	
	public void cleanup() {
		courseApplication = ctx.getBean("courseApplicationImpl", CourseApplication.class);
		List<Course> list = courseApplication.list();
		if(list != null){
			for (Course course : list) {
				courseApplication.delete(course);
			}
		}
		
	}
	
}
