package br.com.async.domain.helper.test;

import java.util.Set;

import br.com.async.core.entities.Diary;
import br.com.async.core.entities.SchoolClass;

public class DiaryHelper extends BaseHelper{
	
	
	public static Diary createBasic(){
		
		Diary diary = new Diary();
		diary.setSchoolClasses(SchoolClassesHelper.createBasicList());
		return diary;
	}

}
