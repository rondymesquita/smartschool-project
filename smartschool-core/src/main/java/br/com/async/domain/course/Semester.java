package br.com.async.domain.course;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
@Entity(name = "tb_semester")
public class Semester {

	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "semester_seq", sequenceName = "semester_seq")
	@GeneratedValue(generator = "semester_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	private String name;
	
}
