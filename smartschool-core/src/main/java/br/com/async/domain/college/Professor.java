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
	public String getRegistry() {
		return registry;
	}
	public void setRegistry(String registry) {
		this.registry = registry;
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
