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
import br.com.async.domain.attendance.Diary;
import br.com.async.domain.college.Professor;
import br.com.async.domain.college.Student;
@Data
@Entity(name = "tb_professorship")
public class Professorship {
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "professorship_seq", sequenceName = "professorship_seq")
	@GeneratedValue(generator = "professorship_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Student> studentList;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "professor")
	private Professor professor;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "discipline")
	private Discipline discipline;
	@NonNull
	@Getter
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "diary")
	private Diary diary;
	
	
	
}
