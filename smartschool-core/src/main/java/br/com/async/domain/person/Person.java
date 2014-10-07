package br.com.async.domain.person;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
@Entity(name = "tb_person")
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TEACHER = "TEACHER";
	public static final String MANAGER = "MANAGER";
	public static final String STUDENT = "STUDENT";

	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "person_seq", sequenceName = "person_seq")
	@GeneratedValue(generator = "person_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	private String name;
	@NonNull
	@Getter
	private String cpf;
}
