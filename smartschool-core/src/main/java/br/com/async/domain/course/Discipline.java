package br.com.async.domain.course;

import java.io.Serializable;

import br.com.async.domain.college.Professor;
import br.com.async.domain.college.Student;
import br.com.async.domain.course.Discipline;

public class Discipline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code;
	private String name;
	private Student student;
	private Professor teacher;

	public Discipline(Integer code, String name, Student student,
			Professor teacher) {
		super();
		this.code = code;
		this.name = name;
		this.student = student;
		this.teacher = teacher;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Student getStudent() {
		return student;
	}

	public Professor getTeacher() {
		return teacher;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discipline other = (Discipline) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}

}
