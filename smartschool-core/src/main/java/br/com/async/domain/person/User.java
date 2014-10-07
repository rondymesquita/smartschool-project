package br.com.async.domain.person;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
public class User implements Serializable {

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
	private String username;
	@NonNull
	@Getter
	private String password;
	

}
