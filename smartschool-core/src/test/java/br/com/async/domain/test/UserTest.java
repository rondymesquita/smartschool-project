package br.com.async.domain.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.async.core.application.UserApplication;
import br.com.async.core.entities.User;
import br.com.async.domain.helper.test.UserHelper;

/**
 * Created by rondymesquita on 15/08/2015
 *
 */
public class UserTest extends BaseTest {

	private UserApplication userApplication;

	@Before
	public void before() {
		userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
		cleanup();
	}

	@After
	public void after() {
		cleanup();
	}

	@Test
	public void saveUserTest() throws Exception {
		User user = UserHelper.createBasic();
		Assert.assertTrue(userApplication.save(user));

		User userSaved = userApplication.findByCode(user.getCode());
		Assert.assertEquals(user.toString(), userSaved.toString());

	}

	@Test
	public void updateUserTest() throws Exception {
		User user = UserHelper.createBasic();
		Assert.assertTrue(userApplication.save(user));

		User userToUpdate = userApplication.findByCode(user.getCode());
		userToUpdate = UserHelper.updateBasic(userToUpdate);
		Assert.assertTrue(userApplication.update(userToUpdate));

		User userUpdated = userApplication.findByCode(userToUpdate.getCode());
		Assert.assertEquals(userToUpdate.toString(), userUpdated.toString());

	}

	@Test
	public void listUserTest() throws Exception {
		User user = UserHelper.createBasic();
		Assert.assertTrue(userApplication.save(user));

		List<User> list = userApplication.list();
		for (User u : list) {
			Assert.assertNotNull(u);
			Assert.assertEquals(user.toString(), u.toString());
		}
	}

	@Test
	public void loginTest() throws Exception {
		User user = UserHelper.createBasic();
		Assert.assertTrue(userApplication.save(user));
		Assert.assertTrue(userApplication.login(user.getUsername(), user.getPassword()));
	}

	@Test
	public void findByUsernameTest() throws Exception {
		User user = UserHelper.createBasic();
		Assert.assertTrue(userApplication.save(user));

		User userSaved = userApplication.findByUsername(user.getUsername());
		Assert.assertEquals(user.toString(), userSaved.toString());
	}

	@Test
	public void findByUsernameAndPasswordTest() throws Exception {
		User user = UserHelper.createBasic();
		Assert.assertTrue(userApplication.save(user));

		User userSaved = userApplication.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		Assert.assertEquals(user.toString(), userSaved.toString());
	}

	public void cleanup() {
		userApplication = ctx.getBean("userApplicationImpl", UserApplication.class);
		List<User> list = userApplication.list();
		if (list != null) {
			for (User user : list) {
				userApplication.delete(user);
			}
		}

	}

}
