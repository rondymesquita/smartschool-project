package br.com.async.domain;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.entities.Discipline;

public class DisciplineTest extends BaseTest{

	private static DisciplineApplication disciplineApplication;
	private static AnnotationConfigApplicationContext ctx;
	private static Integer code;

	@BeforeClass
	public static void before() throws IOException {

		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
		
	}
	
	@AfterClass
	public static void afterClass(){
		List<Discipline> list = disciplineApplication.list();
		for (Discipline discipline : list) {
			disciplineApplication.delete(discipline);
		}
	}
	

	@Test
	public void saveDisciplineTest() throws Exception{
		Discipline discipline = new Discipline();
		discipline.setName("Prog 1");
		discipline.setWorkload(120);
		Assert.assertTrue(disciplineApplication.save(discipline));
	}
	
	@Test
	public void findDisciplineByCodeTest() throws Exception{
		
		Discipline discipline = new Discipline();
		discipline.setName("Prog 1");
		discipline.setWorkload(120);
		Assert.assertTrue(disciplineApplication.save(discipline));
		Discipline discipline2 = disciplineApplication.findByCode(discipline.getCode());
		Assert.assertNotNull(discipline2);
	}
	
	@Test
	public void updateDisciplineTest() throws Exception{
		Discipline discipline = new Discipline();
		discipline.setName("Prog 1");
		discipline.setWorkload(120);
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		Discipline discipline2 = disciplineApplication.findByCode(discipline.getCode());
		discipline2.setName("Prog 2");
		Assert.assertTrue(disciplineApplication.update(discipline2));
		
		Discipline disciplineUpdated = disciplineApplication.findByCode(discipline2.getCode());
		Assert.assertEquals(disciplineUpdated.getName(), "Prog 2");
	}
	
	@Test
	public void listDisciplineTest() throws Exception{
		Discipline discipline = new Discipline();
		discipline.setName("Prog 1");
		discipline.setWorkload(120);
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		List<Discipline> list = disciplineApplication.list();
		for (Discipline d : list) {
			Assert.assertNotNull(d);
		} 
	}
	
}
