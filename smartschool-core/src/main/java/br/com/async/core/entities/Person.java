package br.com.async.core.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@Data
@Entity(name = "tb_person")
@EqualsAndHashCode(callSuper=false)
public class Person extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "person_seq", sequenceName = "person_seq")
	@GeneratedValue(generator = "person_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	private String name;
	
	@Getter
	private String cpf;
	
	@Getter
	private String role;
	
	@Getter
	private String email;
}
