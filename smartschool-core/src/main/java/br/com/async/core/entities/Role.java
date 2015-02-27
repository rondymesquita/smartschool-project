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

import org.springframework.security.core.GrantedAuthority;

@Data
@Entity (name = "tb_role")
@EqualsAndHashCode(callSuper=false)
public class Role extends AbstractEntity implements GrantedAuthority,Serializable{

	public static final String TEACHER = "TEACHER";
	public static final String MANAGER = "MANAGER";
	public static final String STUDENT = "STUDENT";
	
	public static final String ROLE_TEACHER = "ROLE_TEACHER";
	public static final String ROLE_MANAGER = "ROLE_MANAGER";
	public static final String ROLE_STUDENT = "ROLE_STUDENT";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "user_seq", sequenceName = "user_seq")
	@GeneratedValue(generator = "user_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	private String authority;


	
	
	
}
