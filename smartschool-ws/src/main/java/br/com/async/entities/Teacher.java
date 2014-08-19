package br.com.async.entities;

import java.io.Serializable;

public class Teacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;
	private Person person;
	private String enrollments;
	private String formation;

	public Teacher(Integer code, Person person, String enrollments,
			String formation) {
		this.code = code;
		this.person = person;
		this.enrollments = enrollments;
		this.formation = formation;
	}

	public Teacher() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(String enrollments) {
		this.enrollments = enrollments;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
