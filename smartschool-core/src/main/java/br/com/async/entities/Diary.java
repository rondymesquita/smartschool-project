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
@Entity(name = "tb_diary")
public class Diary extends AbstractEntity{
	@Getter
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "diary_seq", sequenceName = "diary_seq")
	@GeneratedValue(generator = "diary_seq", strategy = GenerationType.AUTO)
	private Integer code;
	@NonNull
	@Getter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SchoolClass> schoolClassList;
	
}
