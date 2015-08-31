package br.com.async.helper.test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import br.com.async.core.entities.SchoolClass;

/**
 * Created by rondymesquita on 14/08/2015
 *
 */
public class SchoolClassesHelper {

	/**
	 * @return
	 */
	public static Set<SchoolClass> createBasicList() {
		Set<SchoolClass> schoolClasses = new HashSet<SchoolClass>();
		schoolClasses.add(createBasic());
		return schoolClasses;
	}
	
	public static SchoolClass createBasic(){
		SchoolClass class1 = new SchoolClass();
		class1.setContent(UUID.randomUUID().toString());
		return class1;
	}

}
