package br.com.async.core.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
@Data
@Entity(name = "tb_semester")
@EqualsAndHashCode(callSuper=false)
public class Semester extends AbstractEntity {

	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "semester_seq", sequenceName = "semester_seq")
	@GeneratedValue(generator = "semester_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	private String name;
	
}
