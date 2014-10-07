package br.com.async.domain.course;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import br.com.async.domain.attendance.Diary;
import br.com.async.domain.college.Professor;
import br.com.async.domain.college.Student;
@Data
public class Professorship {
	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private List<Student> studentList;
	@NonNull
	@Getter
	private Professor professor;
	@NonNull
	@Getter
	private Discipline discipline;
	@NonNull
	@Getter
	private Diary diary;
	
	
	
}
