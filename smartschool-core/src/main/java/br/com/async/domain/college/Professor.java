package br.com.async.domain.college;

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
import br.com.async.domain.person.Person;
@Data
@Entity (name = "tb_professor")
public class Professor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "professor_seq", sequenceName = "professor_seq")
	@GeneratedValue(generator = "professor_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "person")
	private Person person;
	@NonNull
	@Getter
	private String enrollments;
	@NonNull
	@Getter
	private String registry;
	@NonNull
	@Getter
	private String formation;
}
