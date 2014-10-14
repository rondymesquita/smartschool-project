package br.com.async.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
@Data
@Entity(name = "tb_coursecurriculum")
public class CourseCurriculum extends AbstractEntity{
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "coursecurriculum_seq", sequenceName = "cousecurriculum_seq")
	@GeneratedValue(generator = "coursecurriculum_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Discipline> disciplineList;
}


