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
@Entity(name = "tb_manager")
@EqualsAndHashCode(callSuper=false)
public class Manager extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "manager_seq", sequenceName = "manager_seq")
	@GeneratedValue(generator = "manager_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	private Professor professor;


}
