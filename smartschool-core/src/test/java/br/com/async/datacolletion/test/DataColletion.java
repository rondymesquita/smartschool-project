package br.com.async.datacolletion;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Student;
import br.com.async.domain.test.BaseDeployTest;

public class DataColletion extends BaseDeployTest{

	private static StudentApplication studentApplication;
	private static ProfessorApplication professorApplication;
	private static DisciplineApplication disciplineApplication;
	
	@BeforeClass
	public static void before(){
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
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
	
}
