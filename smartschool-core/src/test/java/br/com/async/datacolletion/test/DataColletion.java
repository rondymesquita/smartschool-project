package br.com.async.datacolletion.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.async.core.application.CourseApplication;
import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.application.SemesterApplication;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Course;
import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Role;
import br.com.async.core.entities.Semester;
import br.com.async.core.entities.Student;
import br.com.async.deploy.test.BaseDeployTest;

public class DataColletion extends BaseDeployTest{

	private static StudentApplication studentApplication;
	private static ProfessorApplication professorApplication;
	private static DisciplineApplication disciplineApplication;
	private static SemesterApplication semesterApplication;
	private static CourseApplication courseApplication;
	private static ProfessorshipApplication professorshipApplication;
	
	@BeforeClass
	public static void before(){
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
		semesterApplication = ctx.getBean("semesterApplicationImpl", SemesterApplication.class);
		courseApplication = ctx.getBean("courseApplicationImpl", CourseApplication.class);
		professorshipApplication = ctx.getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
	}
	
	@Test
	public void saveCourses() throws Exception{
		Course course = new Course();
		course.setName("Sistemas de Informação");
		Assert.assertTrue(courseApplication.save(course));
		
		course = new Course();
		course.setName("Ciências Contábeis");
		Assert.assertTrue(courseApplication.save(course));
		
		course = new Course();
		course.setName("Enfermagem");
		Assert.assertTrue(courseApplication.save(course));
		
		course = new Course();
		course.setName("Farmácia");
		Assert.assertTrue(courseApplication.save(course));
		
		course = new Course();
		course.setName("Arquitetura");
		Assert.assertTrue(courseApplication.save(course));
		
		course = new Course();
		course.setName("Ciência da Computação");
		Assert.assertTrue(courseApplication.save(course));
		
		course = new Course();
		course.setName("Direito");
		Assert.assertTrue(courseApplication.save(course));
	}
	
	@Test
	public void saveSemesters() throws Exception{
		Semester semester = new Semester();
		semester.setName("2010.1");
		Assert.assertTrue(semesterApplication.save(semester));
		
		semester = new Semester();
		semester.setName("2010.2");
		Assert.assertTrue(semesterApplication.save(semester));
		
		semester = new Semester();
		semester.setName("2011.1");
		Assert.assertTrue(semesterApplication.save(semester));
		
		semester = new Semester();
		semester.setName("2011.2");
		Assert.assertTrue(semesterApplication.save(semester));
	}
	
	@Test
	public void saveStudents() throws Exception{
		Student student = new Student();
		student.setRegistry("1111");
		Person person = new Person();
		person.setName("Homem de Gelo");
		person.setCpf("1111");
		person.setEmail("bob@email.com");
		student.setPerson(person);
		Assert.assertTrue(studentApplication.save(student));
		
		student = new Student();
		student.setRegistry("2222");
		person = new Person();
		person.setName("Lince negra");
		person.setCpf("2222");
		person.setEmail("lince@email.com");
		student.setPerson(person);
		Assert.assertTrue(studentApplication.save(student));
		
		student = new Student();
		student.setRegistry("3333");
		person = new Person();
		person.setName("Noturno");
		person.setCpf("3333");
		person.setEmail("noturno@email.com");
		student.setPerson(person);
		Assert.assertTrue(studentApplication.save(student));
		
		student = new Student();
		student.setRegistry("4444");
		person = new Person();
		person.setName("Vampira");
		person.setCpf("4444");
		person.setEmail("vampira@email.com");
		student.setPerson(person);
		Assert.assertTrue(studentApplication.save(student));
		
		student = new Student();
		student.setRegistry("5555");
		person = new Person();
		person.setName("Mercúrio");
		person.setCpf("5555");
		person.setEmail("mercurio@email.com");
		student.setPerson(person);
		Assert.assertTrue(studentApplication.save(student));
		
		
		student = new Student();
		student.setRegistry("7777");
		person = new Person();
		person.setName("Feiticeira Escarlate");
		person.setCpf("7777");
		person.setEmail("feiticeira@email.com");
		student.setPerson(person);
		Assert.assertTrue(studentApplication.save(student));
	}
	
	@Test
	public void saveProfessors() throws Exception{
		Professor professor = new Professor();
		professor.setRegistry("111");
		professor.setFormation("Msc");
		professor.setEnrollments("111");
		Person person = new Person();
		person.setName("Wolverine");
		person.setCpf("111");
		person.setEmail("wolverine@email.com");
		person.setRole(Role.ROLE_MANAGER);
		professor.setPerson(person);
		Assert.assertTrue(professorApplication.save(professor));
		
		professor = new Professor();
		professor.setRegistry("222");
		professor.setFormation("Msc");
		professor.setEnrollments("222");
		person = new Person();
		person.setName("Tempestade");
		person.setCpf("222");
		person.setEmail("tempestade@email.com");
		person.setRole(Role.ROLE_MANAGER);
		professor.setPerson(person);
		Assert.assertTrue(professorApplication.save(professor));
		
		professor = new Professor();
		professor.setRegistry("333");
		professor.setFormation("Msc");
		professor.setEnrollments("333");
		person = new Person();
		person.setName("Jean Grey");
		person.setCpf("333");
		person.setEmail("jeangrey@email.com");
		person.setRole(Role.ROLE_MANAGER);
		professor.setPerson(person);
		Assert.assertTrue(professorApplication.save(professor));
		
		professor = new Professor();
		professor.setRegistry("444");
		professor.setFormation("Msc");
		professor.setEnrollments("444");
		person = new Person();
		person.setName("Professor Xavier");
		person.setCpf("444");
		person.setEmail("x@email.com");
		person.setRole(Role.ROLE_MANAGER);
		professor.setPerson(person);
		Assert.assertTrue(professorApplication.save(professor));
		
		professor = new Professor();
		professor.setRegistry("555");
		professor.setFormation("Msc");
		professor.setEnrollments("555");
		person = new Person();
		person.setName("Fera");
		person.setCpf("555");
		person.setEmail("fera@email.com");
		person.setRole(Role.ROLE_MANAGER);
		professor.setPerson(person);
		Assert.assertTrue(professorApplication.save(professor));
		
		professor = new Professor();
		professor.setRegistry("777");
		professor.setFormation("Msc");
		professor.setEnrollments("777");
		person = new Person();
		person.setName("Magneto");
		person.setCpf("777");
		person.setEmail("magneto@email.com");
		person.setRole(Role.ROLE_MANAGER);
		professor.setPerson(person);
		Assert.assertTrue(professorApplication.save(professor));
	}
	
	@Test
	public void saveDisciplines() throws Exception{
		
		Discipline discipline = new Discipline();
		discipline.setName("Banco de Dados");
		discipline.setWorkload(10);		
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		discipline = new Discipline();
		discipline.setName("Programação 1");
		discipline.setWorkload(10);		
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		discipline = new Discipline();
		discipline.setName("Marketing");
		discipline.setWorkload(10);		
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		discipline = new Discipline();
		discipline.setName("Matemática Discreta");
		discipline.setWorkload(10);		
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		discipline = new Discipline();
		discipline.setName("Tecnologias Web");
		discipline.setWorkload(10);		
		Assert.assertTrue(disciplineApplication.save(discipline));

		discipline = new Discipline();
		discipline.setName("Programação 2");
		discipline.setWorkload(10);		
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		discipline = new Discipline();
		discipline.setName("Sistemas de Informação");
		discipline.setWorkload(10);		
		Assert.assertTrue(disciplineApplication.save(discipline));
		
		discipline = new Discipline();
		discipline.setName("Gerência de Projetos");
		discipline.setWorkload(10);		
		Assert.assertTrue(disciplineApplication.save(discipline));
	}
	
//	@Test
//	public void saveProfessorship() throws Exception{
//		Professorship professorship = new Professorship();
//		professorship.setCourse(courseApplication.findByCode(1));
//		List<Student> list1 = new ArrayList<Student>();
//		list1.add(studentApplication.findByCode(1));
//		list1.add(studentApplication.findByCode(2));
//		professorship.setStudents(new HashSet<Student>(list1));
//		professorship.setSemester(semesterApplication.findByCode(1));
//		professorship.setDiscipline(disciplineApplication.findByCode(1));
//		professorship.setProfessor(professorApplication.findByCode(1));
//		professorshipApplication.save(professorship);
//		
//	 	professorship = new Professorship();
//		professorship.setCourse(courseApplication.findByCode(2));
//		List<Student> list2 = new ArrayList<Student>();
//		list2.add(studentApplication.findByCode(3));
//		list2.add(studentApplication.findByCode(4));
//		professorship.setStudents(new HashSet<Student>(list2));
//		professorship.setSemester(semesterApplication.findByCode(2));
//		professorship.setDiscipline(disciplineApplication.findByCode(2));
//		professorship.setProfessor(professorApplication.findByCode(2));
//		professorshipApplication.save(professorship);
//		
//	}
	
}
