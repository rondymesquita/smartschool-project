package br.com.async.core.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@Data
@Entity(name = "tb_schoolclass")
@EqualsAndHashCode(callSuper=false)
public class SchoolClass extends AbstractEntity {
	
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "schoolclass_seq", sequenceName = "schoolclass_seq")
	@GeneratedValue(generator = "schoolclass_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	private String content;
	
//	@Getter
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private Set<Attendance> attendances;
	
	@Getter
	@OneToMany
	private Set<Student> studentsAttendance;
	
	
	
}
