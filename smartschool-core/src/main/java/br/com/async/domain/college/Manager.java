package br.com.async.domain.college;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
public class Manager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private Professor professor;


}
