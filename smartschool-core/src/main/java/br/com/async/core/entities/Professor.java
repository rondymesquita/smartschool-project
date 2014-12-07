package br.com.async.core.entities;

import java.io.Serializable;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
@Data
@Entity (name = "tb_professor")
@EqualsAndHashCode(callSuper=false)
public class Professor extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "professor_seq", sequenceName = "professor_seq")
	@GeneratedValue(generator = "professor_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "person")
	private Person person;
	
	@Getter
	private String enrollments;
	
	@Getter
	private String registry;
	
	@Getter
	private String formation;
}
