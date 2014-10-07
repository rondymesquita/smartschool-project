package br.com.async.domain.course;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import br.com.async.domain.college.Manager;
@Data
public class CourseCurriculumSemester {
	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private Manager manager;
	@NonNull
	@Getter
	private Course course;
	@NonNull
	@Getter
	private Semester semester;
	@NonNull
	@Getter
	private	List<Professorship> professorshipList;
	
}
