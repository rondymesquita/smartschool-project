package br.com.async.domain.helper.test;

import java.util.UUID;

import br.com.async.core.application.DisciplineApplication;
import br.com.async.core.entities.Discipline;

public class DisciplineHelper extends BaseHelper{

	private static DisciplineApplication disciplineApplication;
	
	private static void before(){
		config();
		disciplineApplication = ctx.getBean("disciplineApplicationImpl", DisciplineApplication.class);
	}
	
	
	public static Discipline createBasic() {

		String name = UUID.randomUUID().toString();
		Integer workload = Math.max(1, 1);
		Discipline discipline = new Discipline();
		discipline.setName(name);
		discipline.setWorkload(workload);
		return discipline;
	}

	/**
	 * @return
	 */
	public static Discipline saveBasic() {
		before();
		Discipline discipline = createBasic();
		@SuppressWarnings("unused")
		boolean saved = disciplineApplication.save(discipline);
		Discipline d = disciplineApplication.findByCode(discipline.getCode());
		return d;
	}

}
