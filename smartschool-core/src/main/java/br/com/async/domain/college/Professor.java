package br.com.async.domain.college;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import br.com.async.domain.person.Person;
@Data
public class Professor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private Person person;
	@NonNull
	@Getter
	private String enrollments;
	@NonNull
	@Getter
	private String registry;
	@NonNull
	@Getter
	private String formation;
}
