package br.com.async.domain.helper.test;

import java.util.UUID;

import br.com.async.core.entities.Person;

/**
 * Created by rondymesquita on 15/08/2015
 *
 */
public class PersonHelper {

	/**
	 * @return
	 */
	public static Person createBasic() {
		Person person = new Person();
		person.setName(UUID.randomUUID().toString());
		person.setCpf(UUID.randomUUID().toString());
		person.setEmail(UUID.randomUUID().toString());
		return person;
	}
	
	public static Person createBasicWithName(String name) {
		Person person = new Person();
		person.setName(name);
		person.setCpf(UUID.randomUUID().toString());
		person.setEmail(UUID.randomUUID().toString());
		return person;
	}

	/**
	 * @param person
	 * @return
	 */
	public static Person updateBasic(Person person) {
		person.setName(UUID.randomUUID().toString());
		person.setCpf(UUID.randomUUID().toString());
		person.setEmail(UUID.randomUUID().toString());
		return person;
	}

}
