package br.com.async.core.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@Data
@Entity(name = "tb_diary")
@EqualsAndHashCode(callSuper=false)
public class Diary extends AbstractEntity{
	
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "diary_seq", sequenceName = "diary_seq")
	@GeneratedValue(generator = "diary_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SchoolClass> schoolClasses;
	
}
