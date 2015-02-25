package br.com.async.domain;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
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
import br.com.async.core.entities.SchoolClass;
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
	public void saveProfessorshipTest() throws Exception{
		Professor professor = new Professor();
		Person personProfessor = new Person();
		personProfessor.setName("John");
		professor.setPerson(personProfessor);
		Assert.assertTrue(professorApplication.save(professor));
		professor = professorApplication.findByCode(professor.getCode());
		

		Student student = new Student();
		student.setRegistry("abc");
		Person personStudent = new Person();
		personStudent.setName("Peter");
		student.setPerson(personStudent);
		Assert.assertTrue(studentApplication.save(student));
		student = studentApplication.findByCode(student.getCode());
		Set<Student> students = new HashSet<Student>();
		students.add(student);
		
		Discipline discipline = new Discipline();
		discipline.setName("Prog 1");
		Assert.assertTrue(disciplineApplication.save(discipline));
		discipline = disciplineApplication.findByCode(discipline.getCode());
		
		Diary diary = new Diary();
		Set<SchoolClass> schoolClasses = new HashSet<SchoolClass>();
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setContent("Aula 1");
		schoolClass.setStudentsAttendance(students);
		
		schoolClasses.add(schoolClass);
		diary.setSchoolClasses(schoolClasses);
		
		Professorship professorship = new Professorship();
		professorship.setProfessor(professor);
		professorship.setStudents(students);
		professorship.setDiscipline(discipline);
		professorship.setDiary(diary);
		professorshipApplication.save(professorship);
		code = professorship.getCode();
		
		
	}
	
//	@Test
//	public void listTest() {
//		List<Professorship> lista = professorshipApplication.list();
//		Professorship professorship = professorshipApplication.findByCode(1);
//			
//			System.out.println(professorship.getProfessor());
//			System.out.println(professorship.getDiscipline());
//			System.out.println(professorship.getDiary());
//			
//			Set<Student> students = professorship.getStudents();
//			System.out.println("==== Estudantes Matriculados ===");
//			for (Student student : students) {
//				System.out.println(student.getCode());
//			}
//			
//			Set<SchoolClass> schoolClasses = professorship.getDiary().getSchoolClasses();
//			System.out.println("==== Estudantes Presentes ===");
//			for (SchoolClass schoolClass : schoolClasses) {
//				Set<Student> attendace = schoolClass.getStudentsAttendance();
//				for (Student student : attendace) {
//					System.out.println(student.getCode());
//				}
//			}
//
//	}
	

	public void findByCodeProfessorshipTest() throws Exception{
		Professorship professorship = professorshipApplication.findByCode(code);
		Assert.assertNotNull(professorship);
	}
	
	@Test
	public void listProfessorshipTest() throws Exception{
		List<Professorship> list = professorshipApplication.list();
		for (Professorship professorship : list) {
			Assert.assertNotNull(professorship);
		} 
	}
	
	@AfterClass
	public static void afterClass(){
		List<Professorship> list = professorshipApplication.list();
		for (Professorship professorship : list) {
			professorshipApplication.delete(professorship);
		} 
	}

}
