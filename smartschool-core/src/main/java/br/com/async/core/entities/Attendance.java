package br.com.async.core.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@Data
@Entity(name = "tb_attendance")
@EqualsAndHashCode(callSuper=false)
public class Attendance extends AbstractEntity{
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "attendance_seq", sequenceName = "attendance_seq")
	@GeneratedValue(generator = "attendance_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
//	@Getter
//	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@OneToOne
//	@JoinColumn(name = "student")
//	private Student student;
	
	@Getter
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Student> students;
	
	

}
