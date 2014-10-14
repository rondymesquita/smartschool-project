package br.com.async.entities;

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
import lombok.Getter;
import lombok.NonNull;
@Data
@Entity(name = "tb_discipline")
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
	@NonNull
	@Getter
	private String name;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "student")
	private Student student;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "teacher")
	private Professor teacher;
}
