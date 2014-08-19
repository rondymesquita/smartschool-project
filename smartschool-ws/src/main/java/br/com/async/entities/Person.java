package br.com.async.entities;

import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;
	private String name;
	private String cpf;

	public Person(Integer code, String name, String cpf) {
		this.code = code;
		this.name = name;
		this.cpf = cpf;
	}

	public Person() {
		
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
