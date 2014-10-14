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
@Entity (name = "tb_user")
public class User extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "user_seq", sequenceName = "user_seq")
	@GeneratedValue(generator = "user_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "person")
	private Person person;
	@NonNull
	@Getter
	private String username;
	@NonNull
	@Getter
	private String password;
	

}
