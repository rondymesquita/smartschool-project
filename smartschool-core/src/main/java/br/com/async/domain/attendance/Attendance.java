package br.com.async.domain.attendance;

import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import br.com.async.domain.college.Student;
@Data
public class Attendance {
	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private Student student;

}
