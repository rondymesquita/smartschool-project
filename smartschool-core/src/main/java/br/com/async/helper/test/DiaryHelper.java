package br.com.async.helper.test;

import br.com.async.core.entities.Diary;

public class DiaryHelper extends BaseHelper{
	
	
	public static Diary createBasic(){
		
		Diary diary = new Diary();
		diary.setSchoolClasses(SchoolClassesHelper.createBasicList());
		return diary;
	}

}
