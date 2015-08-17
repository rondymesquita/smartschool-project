package br.com.async.domain.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.async.core.application.SemesterApplication;
import br.com.async.core.entities.Semester;
import br.com.async.domain.helper.test.SemesterHelper;

@RunWith(MockitoJUnitRunner.class)
public class SemesterTest extends BaseTest{

	private SemesterApplication semesterApplication;

	@Before
	public void before(){
		semesterApplication = ctx.getBean("semesterApplicationImpl", SemesterApplication.class);
		cleanup();
	}
	
	@After
	public void after(){
		cleanup();
	}
	

	@Test
	public void saveSemesterTest() throws Exception{
		Semester semester = SemesterHelper.createBasic();
		Assert.assertTrue(semesterApplication.save(semester));
		
		Semester semesterSaved = semesterApplication.findByCode(semester.getCode());
		Assert.assertEquals(semester.toString(), semesterSaved.toString());
		
	}
	
	@Test
	public void updateSemesterTest() throws Exception{
		
		Semester semester = SemesterHelper.createBasic();
		Assert.assertTrue(semesterApplication.save(semester));
		
		Semester semesterToUpdate = semesterApplication.findByCode(semester.getCode());
		semesterToUpdate = SemesterHelper.updateBasic(semesterToUpdate);
		Assert.assertTrue(semesterApplication.update(semesterToUpdate));
		
		Semester semesterUpdated = semesterApplication.findByCode(semesterToUpdate.getCode());
		Assert.assertEquals(semesterToUpdate.toString(), semesterUpdated.toString());
		
	}
	
	@Test
	public void listSemesterTest() throws Exception{
		Semester semester = SemesterHelper.createBasic();
		Assert.assertTrue(semesterApplication.save(semester));
		
		List<Semester> list = semesterApplication.list();
		for (Semester s : list) {
			Assert.assertNotNull(s);
			Assert.assertEquals(semester.toString(), s.toString());
		} 
	}
	
	@Test
	public void searchByCodeOrNameWithParamCode() throws Exception{
		Semester semester = SemesterHelper.createBasic();
		Assert.assertTrue(semesterApplication.save(semester));
		
		List<Semester> semesterSearched = semesterApplication.searchByCodeOrName(semester.getCode().toString());
		Assert.assertEquals(semester.toString(), semesterSearched.get(0).toString());  
	}
	
	@Test
	public void searchByCodeOrNameWithParamName() throws Exception{
		Semester semester = SemesterHelper.createBasic();
		Assert.assertTrue(semesterApplication.save(semester));
		
		List<Semester> semesterSearched = semesterApplication.searchByCodeOrName(semester.getName());
		Assert.assertEquals(semester.toString(), semesterSearched.get(0).toString());  
	}
	
	
	public void cleanup() {
		semesterApplication = ctx.getBean("semesterApplicationImpl", SemesterApplication.class);
		List<Semester> list = semesterApplication.list();
		if(list != null){
			for (Semester semester : list) {
				semesterApplication.delete(semester);
			}
		}
		
	}
	
}
