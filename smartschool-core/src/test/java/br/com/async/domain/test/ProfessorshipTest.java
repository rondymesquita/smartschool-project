package br.com.async.domain.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.application.StudentApplication;
import br.com.async.core.entities.Diary;
import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.SchoolClass;
import br.com.async.core.entities.Student;
import br.com.async.domain.helper.test.DisciplineHelper;
import br.com.async.domain.helper.test.ProfessorHelper;
import br.com.async.domain.helper.test.StudentHelper;

public class ProfessorshipTest extends BaseTest{
	
	private static ProfessorshipApplication professorshipApplication;
	private static ProfessorApplication professorApplication;
	private static StudentApplication studentApplication;
	private static DisciplineApplication disciplineApplication;

	@BeforeClass
	public static void before() throws IOException {

		professorshipApplication = ctx.getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
		studentApplication = ctx.getBean("studentApplicationImpl", StudentApplication.class);
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
		
	}
	
	@AfterClass
	public static void afterClass(){
		List<Professorship> list = professorshipApplication.list();
		for (Professorship professorship : list) {
			professorship.setDiary(null);
			professorship.setDiscipline(null);
			professorship.setProfessor(null);
			professorship.setStudents(null);
			professorshipApplication.delete(professorship);
		}
	}
	
	@Test
	public void saveProfessorshipTest() throws Exception{
		
		String content = UUID.randomUUID().toString();
		
		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));
		Professor professorSaved = professorApplication.findByCode(professor.getCode());
		

		Student student = StudentHelper.createBasic();
		Assert.assertTrue(studentApplication.save(student));
		Student studentSaved = studentApplication.findByCode(student.getCode());
		
		Set<Student> students = new HashSet<Student>();
		students.add(studentSaved);
		
		Discipline discipline = DisciplineHelper.createBasic();
		Assert.assertTrue(disciplineApplication.save(discipline));
		Discipline disciplineSaved = disciplineApplication.findByCode(discipline.getCode());
		
		Diary diary = new Diary();
		Set<SchoolClass> schoolClasses = new HashSet<SchoolClass>();
		SchoolClass schoolClass = new SchoolClass();
		schoolClass.setContent(content);
		schoolClass.setStudentsAttendance(students);
		
		schoolClasses.add(schoolClass);
		diary.setSchoolClasses(schoolClasses);
		
		Professorship professorship = new Professorship();
		professorship.setProfessor(professorSaved);
		professorship.setStudents(students);
		professorship.setDiscipline(disciplineSaved);
		professorship.setDiary(diary);
		professorshipApplication.save(professorship);
		
		Professorship professorshipSaved = professorshipApplication.findByCode(professorship.getCode());
		Assert.assertNotNull(professorshipSaved);
		
		ArrayList<SchoolClass> schoolClassesSaved = new ArrayList<SchoolClass>(professorshipSaved.getDiary().getSchoolClasses());
		Assert.assertEquals(schoolClassesSaved.get(0).getContent(), content);
		Assert.assertEquals(professorshipSaved.getProfessor().getPerson().getName(), professorSaved.getPerson().getName());
		Assert.assertEquals(professorshipSaved.getDiscipline().getName(), disciplineSaved.getName());
		ArrayList<Student> studentsSaved = new ArrayList<Student>(professorshipSaved.getStudents());
		Assert.assertEquals(studentsSaved.get(0).getPerson().getName(), studentSaved.getPerson().getName());
		
	}
	
	

	
	@Test
	public void listProfessorshipTest() throws Exception{
		List<Professorship> list = professorshipApplication.list();
		for (Professorship professorship : list) {
			Assert.assertNotNull(professorship);
		} 
	}
	
//	@AfterClass
//	public static void afterClass(){
//		List<Professorship> list = professorshipApplication.list();
//		for (Professorship professorship : list) {
//			professorshipApplication.delete(professorship);
//		} 
//	}

}
