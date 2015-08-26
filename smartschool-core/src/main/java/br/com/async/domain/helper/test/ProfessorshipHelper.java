package br.com.async.domain.helper.test;

import java.util.List;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.ProfessorshipApplication;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Student;

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
	
	public static Professorship updateBasic(Professorship professorship){
		
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
		before();
		
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
	
	/**
	 * 
	 */
	public static void cleanup() {
		config();
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
