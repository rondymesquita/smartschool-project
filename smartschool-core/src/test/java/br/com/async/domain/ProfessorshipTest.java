package br.com.async.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
	public void saveEntities() throws Exception{
		Student student = new Student();
		student.setRegistry("abc");
		Person person = new Person();
		person.setName("John");
		person.setCpf("123");
		student.setPerson(person);
		studentApplication.save(student);
		
		student = new Student();
		student.setRegistry("abc");
		Person person2 = new Person();
		person2.setName("Peter");
		person2.setCpf("123");
		student.setPerson(person2);
		studentApplication.save(student);
		
		Professor professor = new Professor();
		professor.setEnrollments("Enrollment");
		professor.setRegistry("123");
		professor.setFormation("msc");
		Person person3 = new Person();
		person3.setName("John");
		person3.setCpf("123");
		professor.setPerson(person3);
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
		
		Set<Student> students = new HashSet<Student>();
		students.add(studentApplication.findByCode(1));
		students.add(studentApplication.findByCode(2));
		
		Discipline discipline = new Discipline();
		discipline.setCode(1);
		
		Diary diary = new Diary();
		Set<SchoolClass> schoolClasses = new HashSet<SchoolClass>();
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setContent("Aula 1");
//		List<String> studentsAttendance = new ArrayList<String>();
//		studentsAttendance.add(student.getCode().toString());
		Set<Student> studentsAttendance = new HashSet<Student>();
		studentsAttendance.add(studentApplication.findByCode(1));
		schoolClass.setStudentsAttendance(studentsAttendance);
		
		schoolClasses.add(schoolClass);
		diary.setSchoolClasses(schoolClasses);
		
		Professorship professorship = new Professorship();
		professorship.setProfessor(professor);
		professorship.setStudents(students);
		professorship.setDiscipline(discipline);
		professorship.setDiary(diary);
		professorshipApplication.save(professorship);
		
		
	}
	
	@Test
	public void listTest() {
		List<Professorship> lista = professorshipApplication.list();
//		for (Professorship professorship : lista) {
		Professorship professorship = professorshipApplication.findByCode(1);
			
			System.out.println(professorship.getProfessor());
			System.out.println(professorship.getDiscipline());
			System.out.println(professorship.getDiary());
			
			Set<Student> students = professorship.getStudents();
			System.out.println("==== Estudantes Matriculados ===");
			for (Student student : students) {
				System.out.println(student.getCode());
			}
			
			Set<SchoolClass> schoolClasses = professorship.getDiary().getSchoolClasses();
			System.out.println("==== Estudantes Presentes ===");
			for (SchoolClass schoolClass : schoolClasses) {
				Set<Student> attendace = schoolClass.getStudentsAttendance();
				for (Student student : attendace) {
					System.out.println(student.getCode());
				}
			}
//		}

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
