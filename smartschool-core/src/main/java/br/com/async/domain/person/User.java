package br.com.async.domain.person;

import java.io.Serializable;

import br.com.async.domain.person.Person;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;
	private Person person;
	private String username;
	private String password;
	

}
