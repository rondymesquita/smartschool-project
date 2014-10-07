package br.com.async.domain.course;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
public class CourseCurriculum {
	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private List<Discipline> disciplineList;
}


