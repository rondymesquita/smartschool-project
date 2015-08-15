package br.com.async.domain.helper.test;

import java.util.UUID;

import br.com.async.core.entities.Person;
import br.com.async.core.entities.User;

/**
 * Created by rondymesquita on 15/08/2015
 *
 */
public class UserHelper {

	/**
	 * @return
	 */
	public static User createBasic() {
		User user = new User();
		Person person = PersonHelper.createBasic();
		user.setPerson(person);
		user.setUsername(person.getEmail());
		user.setPassword(UUID.randomUUID().toString());
		return user;
	}
	
	public static User createBasic(Person person) {
		User user = new User();
		user.setPerson(person);
		user.setUsername(person.getEmail());
		user.setPassword(UUID.randomUUID().toString());
		return user;
	}

	public static User updateBasic(User userToUpdate) {
		Person person = PersonHelper.updateBasic(userToUpdate.getPerson());
		userToUpdate.setPerson(person);
		userToUpdate.setUsername(person.getEmail());
		userToUpdate.setPassword(UUID.randomUUID().toString());
		return userToUpdate;
	}

	/**
	 * @param person
	 * @return
	 */
	public static User updateBasic(Person person) {
		User user = new User();
		user.setPerson(person);
		user.setUsername(person.getEmail());
		user.setPassword(UUID.randomUUID().toString());
		return user;
	}
	
//	public static User updateBasic(User userToUpdate, Person person) {
//		Person person = PersonHelper.updateBasic(userToUpdate.getPerson());
//		userToUpdate.setPerson(person);
//		userToUpdate.setUsername(person.getEmail());
//		userToUpdate.setPassword(UUID.randomUUID().toString());
//		return userToUpdate;
//	}

}
