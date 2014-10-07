package br.com.async.domain.attendance;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
public class SchoolClass {
	@Getter
	private UUID code = UUID.randomUUID();
	@NonNull
	@Getter
	private String content;
	@NonNull
	@Getter
	private List<Attendance> attendanceList;
	
}
