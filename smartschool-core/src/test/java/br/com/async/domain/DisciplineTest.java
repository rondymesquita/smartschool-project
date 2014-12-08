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
		
		Discipline discipline = new Discipline();
		discipline.setName("Prog 1");
		discipline.setWorkload(120);
		disciplineApplication.save(discipline);
		code = discipline.getCode();
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
	public void findByCodeDisciplineTest() throws Exception{
		
		Discipline discipline = disciplineApplication.findByCode(code);
		Assert.assertNotNull(discipline);
	}
	
	@Test
	public void updateDisciplineTest() throws Exception{
		Discipline discipline = new Discipline();
		discipline.setCode(code);
		discipline.setName("Prog 2");
		discipline.setWorkload(120); 
		Assert.assertTrue(disciplineApplication.update(discipline));
		
		Discipline disciplineUpdated = disciplineApplication.findByCode(discipline.getCode());
		Assert.assertEquals(disciplineUpdated.getName(), "Prog 2");
	}
	
	@Test
	public void listDisciplineTest() throws Exception{
		List<Discipline> list = disciplineApplication.list();
		for (Discipline discipline : list) {
			System.out.println(discipline.getCode());
		} 
	}
	
}
