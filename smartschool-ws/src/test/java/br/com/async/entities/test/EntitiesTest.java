package br.com.async.entities.test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import br.com.async.entities.AuthUser;
import br.com.async.entities.ChangePasswordUser;
import br.com.async.entities.HTTPMethod;
import br.com.async.entities.Permission;
import br.com.async.util.ResponseData;

/**
 * Created by rondymesquita on 16/08/2015
 *
 */
public class EntitiesTest{
	
	@Test
	public void compareTest() throws Exception{
		asyncEntityTestFor(AuthUser.class);
		asyncEntityTestFor(ChangePasswordUser.class);
	}
	
	private static void asyncEntityTestFor(Class clazz){
		new BeanTester().testBean(clazz);
		EqualsVerifier.forClass(clazz).suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
	}

}
