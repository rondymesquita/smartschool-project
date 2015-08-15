package br.com.async.domain.test;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.entities.Discipline;
import br.com.async.domain.helper.test.DisciplineHelper;

@RunWith(MockitoJUnitRunner.class)
public class DisciplineTest extends BaseTest{

	private DisciplineApplication disciplineApplication;

	@Before
	public void before(){
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
		cleanup();
	}
	
	@After
	public void after(){
		cleanup();
	}
	

	@Test
	public void saveDisciplineTest() throws Exception{
		Discipline discipline = DisciplineHelper.createBasic();
		Assert.assertTrue(disciplineApplication.save(discipline));
		Discipline disciplineSaved = disciplineApplication.findByCode(discipline.getCode());
		Assert.assertEquals(discipline.getName(), disciplineSaved.getName());
		Assert.assertEquals(discipline.getWorkload(), disciplineSaved.getWorkload());
	}
	
	@Test
	public void findDisciplineByCodeTest() throws Exception{
		
		Discipline discipline = DisciplineHelper.createBasic();
		Assert.assertTrue(disciplineApplication.save(discipline));
		Integer code = discipline.getCode();
		Discipline disciplineSaved = disciplineApplication.findByCode(code);
		Assert.assertNotNull(disciplineSaved);
		Assert.assertEquals(code, disciplineSaved.getCode());
	}
	
	@Test
	public void updateDisciplineTest() throws Exception{
		
		String name = UUID.randomUUID().toString();
		Integer workload = Math.max(1, 1);
		
		Discipline discipline = DisciplineHelper.createBasic();
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		Discipline disciplineToUpdate = disciplineApplication.findByCode(discipline.getCode());
		disciplineToUpdate.setName(name);
		disciplineToUpdate.setWorkload(workload);
		Assert.assertTrue(disciplineApplication.update(disciplineToUpdate));
		
		Discipline disciplineUpdated = disciplineApplication.findByCode(disciplineToUpdate.getCode());
		Assert.assertEquals(disciplineUpdated.getName(), name);
		Assert.assertEquals(disciplineUpdated.getWorkload(), workload);
	}
	
	@Test
	public void listDisciplineTest() throws Exception{
		Discipline discipline = DisciplineHelper.createBasic();
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		List<Discipline> list = disciplineApplication.list();
		for (Discipline d : list) {
			Assert.assertNotNull(d);
			Assert.assertEquals(discipline.getName(), d.getName());
			Assert.assertEquals(discipline.getWorkload(), d.getWorkload());
		} 
	}
	
	@Test
	public void searchByCodeOrNameWithParamCode() throws Exception{
		Discipline discipline = DisciplineHelper.createBasic();
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		List<Discipline> disciplineSearched = disciplineApplication.searchByCodeOrName(discipline.getCode().toString());
		Assert.assertEquals(discipline.toString(), disciplineSearched.get(0).toString());  
	}
	
	@Test
	public void searchByCodeOrNameWithParamName() throws Exception{
		Discipline discipline = DisciplineHelper.createBasic();
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		List<Discipline> disciplineSearched = disciplineApplication.searchByCodeOrName(discipline.getName());
		Assert.assertEquals(discipline.toString(), disciplineSearched.get(0).toString());  
	}
	
	
	public void cleanup() {
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
		List<Discipline> list = disciplineApplication.list();
		if(list != null){
			for (Discipline discipline : list) {
				disciplineApplication.delete(discipline);
			}
		}
		
	}
	
}
