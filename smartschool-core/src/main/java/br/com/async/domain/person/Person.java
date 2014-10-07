package br.com.async.domain.person;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TEACHER = "TEACHER";
	public static final String MANAGER = "MANAGER";
	public static final String STUDENT = "STUDENT";

	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private String name;
	@NonNull
	@Getter
	private String cpf;
}
