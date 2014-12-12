package br.com.async.core.entities;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity(name = "tb_attendanceStudent")
@EqualsAndHashCode(callSuper=false)
public class AttendanceStudent extends Student{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
