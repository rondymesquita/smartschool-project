package br.com.async.domain.helper.test;

import java.util.UUID;

import br.com.async.core.entities.Discipline;

public class DisciplineHelper {
	
	
public static Discipline createBasic(){
		
		String name = UUID.randomUUID().toString();
		Integer workload = Math.max(1, 1);
		Discipline discipline = new Discipline();
		discipline.setName(name);
		discipline.setWorkload(workload);
		return discipline;
	}

}
