package br.com.async.domain.course;

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
import lombok.Getter;
import lombok.NonNull;
import br.com.async.domain.college.Manager;
@Data
@Entity(name = "tb_coursecurriculumsemester")
public class CourseCurriculumSemester {
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "coursecurriculumsemester_seq", sequenceName = "coursecurriculumsemester_seq")
	@GeneratedValue(generator = "coursecurriculumsemester_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "manager")
	private Manager manager;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "course")
	private Course course;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "semester")
	private Semester semester;
	@NonNull
	@Getter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private	List<Professorship> professorshipList;
	
}
