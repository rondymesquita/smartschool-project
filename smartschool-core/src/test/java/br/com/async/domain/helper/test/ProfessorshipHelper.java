package br.com.async.domain.helper.test;

import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.async.config.test.HibernateConfigTest;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professorship;

public class ProfessorshipHelper extends BaseHelper{
	
	private static ProfessorshipApplication professorshipApplication;
	
	private static void before(){
		config();
		professorshipApplication = ctx.getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
	}
	
	public static Professorship createBasic(){
		
		Professorship professorship = new Professorship();
		professorship.setStudents(StudentHelper.saveBasicList());
		professorship.setProfessor(ProfessorHelper.saveBasic());
		professorship.setDiscipline(DisciplineHelper.saveBasic());
		professorship.setDiary(DiaryHelper.createBasic());
		professorship.setSemester(SemesterHelper.saveBasic());
		professorship.setCourse(CourseHelper.saveBasic());
		
		
		return professorship;
	}
	
	public static Professorship saveBasic(){
		before();
		
		Professorship professorship = createBasic();
		professorshipApplication.save(professorship);
		return professorshipApplication.findByCode(professorship.getCode());
	}

	
}
