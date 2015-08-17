package br.com.async.domain.test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import br.com.async.core.entities.Attendance;
import br.com.async.core.entities.Course;
import br.com.async.core.entities.CourseCurriculum;
import br.com.async.core.entities.CourseCurriculumSemester;
import br.com.async.core.entities.Diary;
import br.com.async.core.entities.Discipline;
import br.com.async.core.entities.Manager;
import br.com.async.core.entities.MyUser;
import br.com.async.core.entities.Person;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.SchoolClass;
import br.com.async.core.entities.Semester;
import br.com.async.core.entities.Student;
import br.com.async.core.entities.User;

/**
 * Created by rondymesquita on 16/08/2015
 *
 */
public class EntitiesTest extends BaseTest{
	
	@Test
	public void compareTest() throws Exception{
		asyncEntityTestFor(User.class);
		asyncEntityTestFor(Person.class);
		asyncEntityTestFor(Course.class);
		asyncEntityTestFor(Semester.class);
		asyncEntityTestFor(Student.class);
		asyncEntityTestFor(Professor.class);
		asyncEntityTestFor(Professorship.class);
		asyncEntityTestFor(Diary.class);
		asyncEntityTestFor(SchoolClass.class);
		asyncEntityTestFor(Discipline.class);
		
		//maybe to remove these unused classes from project
		asyncEntityTestFor(MyUser.class);
		asyncEntityTestFor(CourseCurriculum.class);
		asyncEntityTestFor(CourseCurriculumSemester.class);
		asyncEntityTestFor(Manager.class);
		asyncEntityTestFor(Attendance.class);
	}
	
	private static void asyncEntityTestFor(Class clazz){
		new BeanTester().testBean(clazz);
		EqualsVerifier.forClass(clazz).suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
	}

}
