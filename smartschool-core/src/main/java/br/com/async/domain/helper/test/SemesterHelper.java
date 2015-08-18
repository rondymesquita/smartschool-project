package br.com.async.domain.helper.test;

import java.util.List;
import java.util.UUID;

import br.com.async.core.application.SemesterApplication;
import br.com.async.core.entities.Semester;

public class SemesterHelper extends BaseHelper{
	
	private static SemesterApplication semesterApplication;
	
	private static void before(){
		config();
		semesterApplication = ctx.getBean("semesterApplicationImpl", SemesterApplication.class);
	}
	
	public static Semester createBasic(){
		
		String name = UUID.randomUUID().toString();
		
		Semester semester = new Semester();
		semester.setName(name);
		
		return semester;
	}
	
	public static Semester updateBasic(Semester semester){
		semester.setName(UUID.randomUUID().toString());
		return semester;
	}

	/**
	 * @return
	 */
	public static Semester saveBasic() {
		before();
		Semester semester = createBasic();
		semesterApplication.save(semester);
		return semesterApplication.findByCode(semester.getCode());
	}
	
	public static void cleanup(){
		config();
		semesterApplication = ctx.getBean("semesterApplicationImpl", SemesterApplication.class);
		List<Semester> list = semesterApplication.list();
		if(list != null){
			for (Semester semester : list) {
				semesterApplication.delete(semester);
			}
		}
	}

}
