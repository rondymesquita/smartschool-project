package br.com.async.domain.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
@Entity(name = "tb_contact")
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "contact_seq", sequenceName = "contact_seq")
	@GeneratedValue(generator = "contact_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	private String email;
}
