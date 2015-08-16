package br.com.async.domain.helper.test;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;

public class ProfessorshipHelper extends BaseHelper{
	
	private static ProfessorshipApplication professorshipApplication;
	private static ProfessorApplication professorApplication;
	
	
	private static void before(){
		config();
		professorshipApplication = ctx.getBean("professorshipApplicationImpl", ProfessorshipApplication.class);
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
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
	
	public static Professorship createBasicWithProfessor(Professor professor){
		
		Professorship professorship = new Professorship();
		professorship.setStudents(StudentHelper.saveBasicList());
		professorship.setProfessor(professorApplication.findByCode(professor.getCode()));
		professorship.setDiscipline(DisciplineHelper.saveBasic());
		professorship.setDiary(DiaryHelper.createBasic());
		professorship.setSemester(SemesterHelper.saveBasic());
		professorship.setCourse(CourseHelper.saveBasic());
		
		return professorship;
	}
	
	public static Professorship saveBasicWithProfessor(Professor professor){
		before();
		
		Professorship professorship = createBasicWithProfessor(professor);
		professorshipApplication.save(professorship);
		return professorshipApplication.findByCode(professorship.getCode());
	}

	
}
