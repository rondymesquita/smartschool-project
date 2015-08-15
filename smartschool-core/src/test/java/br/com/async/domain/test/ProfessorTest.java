package br.com.async.domain.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.orm.hibernate4.HibernateTemplate;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.Professor;
import br.com.async.core.entities.User;
import br.com.async.core.repository.impl.AbstractRepositoryImpl;
import br.com.async.domain.helper.test.ProfessorHelper;
import br.com.async.domain.helper.test.UserHelper;

public class ProfessorTest extends BaseTest {

	private ProfessorApplication professorApplication;
	private UserApplication userApplication;

	@Before
	public void before() {
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
		userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
		cleanup();
	}

	@After
	public void after() {
		cleanup();
	}

	@Test
	public void saveProfessorTest() throws Exception {
		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));
		Professor professorSaved = professorApplication.findByCode(professor.getCode());
		Assert.assertEquals(professor.toString(), professorSaved.toString());
	}
	
	@Test
	public void saveProfessorAndUserTest() throws Exception {
		Professor professor = ProfessorHelper.createBasic();
		User user = UserHelper.createBasic(professor.getPerson());
		Assert.assertTrue(professorApplication.save(professor, user));
		
		Professor professorSaved = professorApplication.findByCode(professor.getCode());
		Assert.assertEquals(professor.toString(), professorSaved.toString());
	}
	
	@Test
	public void updateProfessorTest() throws Exception {

		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));

		Professor professorToUpdate = professorApplication.findByCode(professor.getCode());
		professorToUpdate = ProfessorHelper.updateBasic(professorToUpdate);
		Assert.assertTrue(professorApplication.update(professorToUpdate));

		Professor professorUpdated = professorApplication.findByCode(professorToUpdate.getCode());
		Assert.assertEquals(professorToUpdate.toString(), professorUpdated.toString());
	}
	
	@Test
	public void updateProfessorAndUserTest() throws Exception {

		Professor professor = ProfessorHelper.createBasic();
		User user = UserHelper.createBasic(professor.getPerson());
		Assert.assertTrue(professorApplication.save(professor, user));

		Professor professorToUpdate = professorApplication.findByCode(professor.getCode());
		professorToUpdate = ProfessorHelper.updateBasic(professorToUpdate);
		User userToUpdate = UserHelper.updateBasic(professorToUpdate.getPerson());
		
		Assert.assertTrue(professorApplication.update(professorToUpdate, userToUpdate));

		Professor professorUpdated = professorApplication.findByCode(professorToUpdate.getCode());
		Assert.assertEquals(professorToUpdate.toString(), professorUpdated.toString());
		
		Assert.assertTrue(userApplication.login(userToUpdate.getUsername(), userToUpdate.getPassword()));  
	}
	
	@Test
	public void findByEmailTest() throws Exception {

		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));

		Professor professorSearched = professorApplication.findByEmail(professor.getPerson().getEmail());
		Assert.assertEquals(professor.toString(), professorSearched.toString());
	}

	@Test
	public void listProfessorTest() throws Exception {
		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));

		List<Professor> list = professorApplication.list();
		for (Professor p : list) {
			Assert.assertNotNull(p);
			Assert.assertEquals(professor.toString(), p.toString());
		}
	}
	
	@Test
	public void searchByCodeOrNameWithParamCode() throws Exception{
		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));
		
		List<Professor> professorSearched = professorApplication.searchByCodeOrName(professor.getCode().toString());
		Assert.assertEquals(professor.toString(), professorSearched.get(0).toString());  
	}
	
	@Test
	public void searchByCodeOrNameWithParamName() throws Exception{
		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));
		
		List<Professor> professorSearched = professorApplication.searchByCodeOrName(professor.getPerson().getName());
		Assert.assertEquals(professor.toString(), professorSearched.get(0).toString());  
	}

	/**
	 * 
	 */
	public void cleanup() {
		
		userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
		List<User> list2 = userApplication.list();
		if(list2 != null){
			for (User user : list2) {
				user.setPerson(null);
				userApplication.delete(user);
			}
		}
		
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
		List<Professor> list = professorApplication.list();
		if (list != null) {
			for (Professor professor : list) {
				professor.setPerson(null);
				professorApplication.delete(professor);
			}
		}
		
		
		
	}
}
