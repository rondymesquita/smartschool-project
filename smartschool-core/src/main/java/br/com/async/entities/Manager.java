package br.com.async.entities;

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
@Entity(name = "tb_manager")
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
	@NonNull
	@Getter
	private Professor professor;


}
