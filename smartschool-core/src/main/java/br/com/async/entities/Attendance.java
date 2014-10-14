package br.com.async.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
@Entity(name = "tb_attendance")
public class Attendance extends AbstractEntity{
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "attendance_seq", sequenceName = "attendance_seq")
	@GeneratedValue(generator = "attendance_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "student")
	private Student student;

}
