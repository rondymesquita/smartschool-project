package br.com.async.core.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@Data
@Entity(name = "tb_professorship")
@EqualsAndHashCode(callSuper=false)
public class Professorship extends AbstractEntity implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "professorship_seq", sequenceName = "professorship_seq")
	@GeneratedValue(generator = "professorship_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	@ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.REFRESH)
	private Set<Student> students;
	
	@Getter
	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.REFRESH)
	@JoinColumn(name = "professor")
	private Professor professor;
	
	@Getter
	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.REFRESH)
	@JoinColumn(name = "discipline")
	private Discipline discipline;
	
	@Getter
	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
	@JoinColumn(name = "diary")
	private Diary diary;
	
	@Getter
	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.REFRESH)
	@JoinColumn(name = "semester")
	private Semester semester;
	
	@Getter
	@OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.REFRESH)
	@JoinColumn(name = "course")
	private Course course;

	
}
