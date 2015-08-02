package br.com.async.domain.helper.test;

import java.util.Set;

import br.com.async.core.entities.Diary;
import br.com.async.core.entities.SchoolClass;

public class DiaryHelper {
	
	
	public static Diary createBasic(){
		
		Diary diary = new Diary();
		//diary.setSchoolClasses(getSchoolClasses());
		return diary;
	}
	
	//private static Set<SchoolClass> getSchoolClasses(){
		//SchoolClass schoolClass = new SchoolClass();
		//schoolClass.setContent("Content");
		//schoolClass.setStudentsAttendance(studentsAttendance);
	//}

}
