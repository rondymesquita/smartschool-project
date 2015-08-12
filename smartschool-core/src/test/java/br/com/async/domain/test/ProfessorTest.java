package br.com.async.domain.test;

import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.async.core.application.ProfessorApplication;
import br.com.async.core.entities.Professor;
import br.com.async.domain.helper.test.ProfessorHelper;

public class ProfessorTest extends BaseTest {

	private ProfessorApplication professorApplication;

	@Before
	public void before() {
		professorApplication = ctx.getBean("professorApplicationImpl", ProfessorApplication.class);
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
		Assert.assertEquals(professor.getRegistry(), professorSaved.getRegistry());
		Assert.assertEquals(professor.getFormation(), professorSaved.getFormation());
		Assert.assertEquals(professor.getEnrollments(), professorSaved.getEnrollments());
		Assert.assertEquals(professor.getPerson().getName(), professorSaved.getPerson().getName());
		Assert.assertEquals(professor.getPerson().getCpf(), professorSaved.getPerson().getCpf());
		Assert.assertEquals(professor.getPerson().getEmail(), professorSaved.getPerson().getEmail());
	}

	@Test
	public void updateProfessorTest() throws Exception {
		String registry = UUID.randomUUID().toString();
		String formation = UUID.randomUUID().toString();
		String enrollments = UUID.randomUUID().toString();
		String name = UUID.randomUUID().toString();
		String cpf = UUID.randomUUID().toString();
		String email = UUID.randomUUID().toString();

		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));

		Professor professorToUpdate = professorApplication.findByCode(professor.getCode());
		professorToUpdate.setRegistry(registry);
		professorToUpdate.setFormation(formation);
		professorToUpdate.setEnrollments(enrollments);
		professorToUpdate.getPerson().setName(name);
		professorToUpdate.getPerson().setCpf(cpf);
		professorToUpdate.getPerson().setEmail(email);

		Assert.assertTrue(professorApplication.update(professorToUpdate));

		Professor professorUpdated = professorApplication.findByCode(professor.getCode());
		Assert.assertEquals(professorUpdated.getRegistry(), registry);
		Assert.assertEquals(professorUpdated.getFormation(), formation);
		Assert.assertEquals(professorUpdated.getEnrollments(), enrollments);
		Assert.assertEquals(professorUpdated.getPerson().getName(), name);
		Assert.assertEquals(professorUpdated.getPerson().getCpf(), cpf);
		Assert.assertEquals(professorUpdated.getPerson().getEmail(), email);
	}

	@Test
	public void listProfessorTest() throws Exception {
		Professor professor = ProfessorHelper.createBasic();
		Assert.assertTrue(professorApplication.save(professor));

		List<Professor> list = professorApplication.list();
		for (Professor p : list) {
			Assert.assertNotNull(p);
		}
	}

	/**
	 * 
	 */
	public void cleanup() {
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
