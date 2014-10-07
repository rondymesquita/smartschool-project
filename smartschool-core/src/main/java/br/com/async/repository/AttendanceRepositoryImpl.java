package br.com.async.repository;

import br.com.async.domain.attendance.Attendance;

public class AttendanceRepositoryImpl extends AbstractRepositoryImpl<Attendance, Integer>{

	public AttendanceRepositoryImpl() {
		super(Attendance.class);
	}

}
