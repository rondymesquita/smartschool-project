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
@Data
@Entity(name = "tb_discipline")
@EqualsAndHashCode(callSuper=false)
public class Discipline extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "discipline_seq", sequenceName = "discipline_seq")
	@GeneratedValue(generator = "discipline_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	private String name;
	
	@Getter
	private Integer workload;
	
//	@Getter
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "student")
//	private Student student;
//	
//	@Getter
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "teacher")
//	private Professor teacher;
}
