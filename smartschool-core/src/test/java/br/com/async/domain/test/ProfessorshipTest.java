package br.com.async.domain.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.async.core.application.CourseApplication;
import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.application.SemesterApplication;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.SchoolClass;
import br.com.async.core.entities.Semester;
import br.com.async.core.entities.Student;
import br.com.async.deploy.test.BaseDeployTest;
import br.com.async.domain.helper.test.ProfessorshipHelper;

public class ProfessorshipTest extends BaseTest {

	private ProfessorshipApplication professorshipApplication;
	private ProfessorApplication professorApplication;
	private StudentApplication studentApplication;
	private DisciplineApplication disciplineApplication;
	private SemesterApplication semesterApplication;
	private CourseApplication courseApplication;

	@Before
	public void before() throws IOException {
		
		professorshipApplication = ctx.getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
		semesterApplication = ctx.getBean("semesterApplicationImpl", SemesterApplication.class);
		courseApplication = ctx.getBean("courseApplicationImpl", CourseApplication.class);
		cleanup();

	}

	@After
	public void after() {
		cleanup();
	}
	
	@Test
	public void searchSemesterByCourse() throws Exception {
		Professorship professorship =  ProfessorshipHelper.saveBasic();
		List<Semester> list = professorshipApplication.searchSemesterByCourse(professorship.getCode().toString());
		for (Semester semester : list) {
			Assert.assertNotNull(semester);
			Assert.assertEquals(professorship.getSemester().toString(), semester.toString());
		}
	}

	@Test
	public void saveProfessorshipTest() throws Exception {

		Professorship professorship = ProfessorshipHelper.createBasic();
		professorshipApplication.save(professorship);

		Professorship professorshipSaved = professorshipApplication.findByCode(professorship.getCode());
		Assert.assertNotNull(professorshipSaved);
		
		//students
		ArrayList<Student> studentsSaved = new ArrayList<Student>(professorshipSaved.getStudents());
		ArrayList<Student> students = new ArrayList<Student>(professorship.getStudents());
		
		Assert.assertEquals(studentsSaved.get(0).getPerson().getName(), students.get(0).getPerson().getName());
		
		//schoolclasses
		ArrayList<SchoolClass> schoolClassesSaved = new ArrayList<SchoolClass>(professorshipSaved.getDiary().getSchoolClasses());
		ArrayList<SchoolClass> schoolClasses = new ArrayList<SchoolClass>(professorship.getDiary().getSchoolClasses());
		
		Assert.assertEquals(schoolClassesSaved.get(0).getContent(), 
				schoolClasses.get(0).getContent());
		
		//professor
		Assert.assertEquals(professorshipSaved.getProfessor().getPerson().getName(), 
				professorship.getProfessor().getPerson().getName());
		
		//discipline
		Assert.assertEquals(professorshipSaved.getDiscipline().getName(), 
				professorship.getDiscipline().getName());
		
		//semester
		Assert.assertEquals(professorshipSaved.getSemester().getName(), 
				professorship.getSemester().getName());
		
		//course
		Assert.assertEquals(professorshipSaved.getCourse().getName(), 
				professorship.getCourse().getName());
		
		

	}
	
	@Test
	public void searchProfessorshipsByProfessorName() throws Exception{
		
		String name = UUID.randomUUID().toString();
		Professorship professorship =  ProfessorshipHelper.saveBasic();
		ProfessorshipHelper.saveBasicWithProfessor(professorship.getProfessor());
		ProfessorshipHelper.saveBasicWithProfessor(professorship.getProfessor());
		ProfessorshipHelper.saveBasicWithProfessor(professorship.getProfessor());
		ProfessorshipHelper.saveBasicWithProfessor(professorship.getProfessor());
		
		List<Professorship> professorshipsSaved = professorshipApplication.searchProfessorshipsByProfessorName(name);
		for (Professorship professorshipSaved : professorshipsSaved) {
			Assert.assertEquals(name, professorshipSaved.getProfessor().getPerson().getName());
		}
	}
	
	@Test
	public void listByCodeOrProfessorOrDisciplineParamProfessorTest() throws Exception {
		
		Professorship professorship =  ProfessorshipHelper.saveBasic();
		List<Professorship> list = professorshipApplication.searchByCodeOrDisciplineOrProfessor(professorship.getProfessor().getPerson().getName());
		
		for (Professorship professorshipSaved : list) {
			Assert.assertNotNull(professorshipSaved);
			Assert.assertEquals(professorship.toString(), professorshipSaved.toString());
		}

	}
	
	@Test
	public void listByCodeOrProfessorOrDisciplineParamDisciplineTest() throws Exception {
		
		Professorship professorship =  ProfessorshipHelper.saveBasic();
		List<Professorship> list = professorshipApplication.searchByCodeOrDisciplineOrProfessor(professorship.getDiscipline().getName());
		
		for (Professorship professorshipSaved : list) {
			Assert.assertNotNull(professorshipSaved);
			Assert.assertEquals(professorship.toString(), professorshipSaved.toString());
		}
	}
	
	@Test
	public void listByCodeOrProfessorOrDisciplineParamCodeTest() throws Exception {
		
		Professorship professorship =  ProfessorshipHelper.saveBasic();
		List<Professorship> list = professorshipApplication.searchByCodeOrDisciplineOrProfessor(professorship.getCode().toString());
		
		for (Professorship professorshipSaved : list) {
			Assert.assertNotNull(professorshipSaved);
			Assert.assertEquals(professorship.toString(), professorshipSaved.toString());
		}
	}
	
	@Test
	public void searchProfessorshipsBySemesterTest() throws Exception {
		
		Professorship professorship =  ProfessorshipHelper.saveBasic();
		List<Professorship> list = professorshipApplication.searchProfessorshipsBySemester(professorship.getSemester().toString());
		
		for (Professorship professorshipSaved : list) {
			Assert.assertNotNull(professorshipSaved);
			Assert.assertEquals(professorship.toString(), professorshipSaved.toString());
		}
	}
	
	
	@Test
	public void listProfessorshipTest() throws Exception {
		Professorship professorship =  ProfessorshipHelper.saveBasic();
		List<Professorship> list = professorshipApplication.list();
		
		for (Professorship professorshipSaved : list) {
			Assert.assertNotNull(professorshipSaved);
			Assert.assertEquals(professorship.toString(), professorshipSaved.toString());
		}
	}

	/**
	 * 
	 */
	public void cleanup() {
		professorshipApplication = ctx.getBean("professorshipApplicationImpl", ProfessorshipApplication.class);

		List<Professorship> list = professorshipApplication.list();
		if (list != null) {
			for (Professorship professorship : list) {
				professorship.setDiary(null);
				professorship.setDiscipline(null);
				professorship.setProfessor(null);
				for (Student student : professorship.getStudents()) {
					student.setPerson(null);
				}
				professorship.setStudents(null);
				professorshipApplication.delete(professorship);
			}
		}
	}

}
