package br.com.async.domain;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.application.StudentApplication;
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
		
	}
	
	@Test
	public void saveEntities() throws Exception{
		Student student = new Student();
		student.setRegistry("abc");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		student.setPerson(person);
		studentApplication.save(student);
		
		Professor professor = new Professor();
		professor.setEnrollments("Enrollment");
		professor.setRegistry("123");
		professor.setFormation("msc");
		Person person2 = new Person();
		person2.setName("John");
		person2.setCpf("123");
		professor.setPerson(person2);
		professorApplication.save(professor);
		
		Discipline discipline = new Discipline();
		discipline.setName("Prog 1");
		discipline.setWorkload(120);
		disciplineApplication.save(discipline);
		
	}
	
	@Test
	public void saveProfessorshipTest() throws Exception{
		Professor professor = new Professor();
		professor.setCode(1);
		
		Student student = new Student();
		student.setCode(1);
		Set<Student> students = new HashSet<Student>();
		students.add(student);
		
		Discipline discipline = new Discipline();
		discipline.setCode(1);
		
		Professorship professorship = new Professorship();
		
		professorship.setProfessor(professor);
		professorship.setStudents(students);
		professorship.setDiscipline(discipline);
		professorshipApplication.save(professorship);
		
		
	}
	
	@Test
	public void listTest() {
		List<Professorship> lista = professorshipApplication.list();
		for (Professorship professorship2 : lista) {
			Set<Student> sets = professorship2.getStudents();
			System.out.println(professorship2.getProfessor());
			System.out.println(professorship2.getDiscipline());
			for (Student student : sets) {
				System.out.println(student.getPerson().getName());
			}
		}

	}
	
	
//	@AfterClass
//	public static void beforeClass() throws IOException {
//		List<Professorship> list = professorshipApplication.list();
//		for (Professorship professorship : list) {
//			professorshipApplication.delete(professorship);
//		} 
//	}

//	@Test
//	public void saveProfessorshipTest() throws Exception{
//		Professorship professorship = new Professorship();
//		professorship.setProfessor(professorApplication.findByCode(professorApplication.list().get(0).getCode()));
//		professorship.setStudentList(studentApplication.list());
//		professorship.setDiary(new Diary());
//		professorship.setDiscipline(disciplineApplication.findByCode(0));
//		Assert.assertTrue(professorshipApplication.save(professorship));
//	}
//	
//	public void findByCodeProfessorshipTest() throws Exception{
//		
//		Professorship professorship = professorshipApplication.findByCode(code);
//		Assert.assertNotNull(professorship);
//	}
//		
//	@Test
//	public void listProfessorshipTest() throws Exception{
//		List<Professorship> list = professorshipApplication.list();
//		for (Professorship professorship : list) {
//			System.out.println(professorship.getCode());
//		} 
//	}

}
