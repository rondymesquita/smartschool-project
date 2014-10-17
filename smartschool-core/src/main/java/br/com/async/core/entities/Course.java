package br.com.async.core.entities;

import java.io.Serializable;

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
@Entity(name = "tb_course")
@EqualsAndHashCode(callSuper=false)
public class Course extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "course_seq", sequenceName = "course_seq")
	@GeneratedValue(generator = "course_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	private String name;


}
