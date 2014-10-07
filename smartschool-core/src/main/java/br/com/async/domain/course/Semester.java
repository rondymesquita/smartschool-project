package br.com.async.domain.course;

import java.util.UUID;

import lombok.Getter;
import lombok.NonNull;
@Data
public class Semester {

	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private String name;
	
}
