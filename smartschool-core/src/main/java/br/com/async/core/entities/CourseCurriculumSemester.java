package br.com.async.core.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
@Data
@Entity(name = "tb_coursecurriculumsemester")
@EqualsAndHashCode(callSuper=false)
public class CourseCurriculumSemester extends AbstractEntity{
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "coursecurriculumsemester_seq", sequenceName = "coursecurriculumsemester_seq")
	@GeneratedValue(generator = "coursecurriculumsemester_seq", strategy = GenerationType.AUTO)
	private Integer code;
	
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "manager")
	private Manager manager;
	
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "course")
	private Course course;
	
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "semester")
	private Semester semester;
	
	@Getter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private	List<Professorship> professorshipList;
	
}
