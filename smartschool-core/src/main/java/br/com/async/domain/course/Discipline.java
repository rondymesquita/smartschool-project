package br.com.async.domain.course;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import br.com.async.domain.college.Professor;
import br.com.async.domain.college.Student;
@Data
public class Discipline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NonNull
	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private String name;
	@NonNull
	@Getter
	private Student student;
	@NonNull
	@Getter
	private Professor teacher;
}
