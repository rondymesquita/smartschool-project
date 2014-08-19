package br.com.async.domain.college;

import java.io.Serializable;

import br.com.async.domain.person.Person;

public class Professor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;
	private Person person;
	private String enrollments;
	private String registry;
	private String formation;

}
