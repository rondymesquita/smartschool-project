package br.com.async.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Diary;
import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Student;

public class ProfessorshipTest extends BaseTest{
	
	private static ProfessorshipApplication professorshipApplication;
	private static ProfessorApplication professorApplication;
	private static StudentApplication studentApplication;
	private static DisciplineApplication disciplineApplication;
	private static AnnotationConfigApplicationContext ctx;
	private static Integer code;

	@BeforeClass
	public static void before() throws IOException {

		ctx = new AnnotationConfigApplicationContext();
		ctx.scan("br.com.async.core");
		ctx.refresh();
		
		professorshipApplication = ctx.getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
		
		Professor professor = new Professor();
		professor.setEnrollments("Enrollment");
		professor.setRegistry("123");
		professor.setFormation("msc");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		professor.setPerson(person);
		professorApplication.save(professor);
		
		Student student = new Student();
		student.setRegistry("abc");
		Person person2 = new Person();
		person2.setName("John");
		person2.setCpf("123");
		student.setPerson(person2);
		studentApplication.save(student);
		
		Discipline discipline = new Discipline();
		discipline.setName("Prog 1");
		discipline.setWorkload(120);
		disciplineApplication.save(discipline);
		
		Professorship professorship = new Professorship();
		professorship.setProfessor(professor);
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(student);
		professorship.setStudentList(studentList);
		professorship.setDiary(new Diary());
		professorship.setDiscipline(disciplineApplication.findByCode(0));
	}
	
	@AfterClass
	public static void beforeClass() throws IOException {
		List<Professorship> list = professorshipApplication.list();
		for (Professorship professorship : list) {
			professorshipApplication.delete(professorship);
		} 
	}

	@Test
	public void saveProfessorshipTest() throws Exception{
		Professorship professorship = new Professorship();
		professorship.setProfessor(professorApplication.findByCode(professorApplication.list().get(0).getCode()));
		professorship.setStudentList(studentApplication.list());
		professorship.setDiary(new Diary());
		professorship.setDiscipline(disciplineApplication.findByCode(0));
		Assert.assertTrue(professorshipApplication.save(professorship));
	}
	
	public void findByCodeProfessorshipTest() throws Exception{
		
		Professorship professorship = professorshipApplication.findByCode(code);
		Assert.assertNotNull(professorship);
	}
		
	@Test
	public void listProfessorshipTest() throws Exception{
		List<Professorship> list = professorshipApplication.list();
		for (Professorship professorship : list) {
			System.out.println(professorship.getCode());
		} 
	}

}
