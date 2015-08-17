package br.com.async.core.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Student;
import br.com.async.core.repository.StudentRepository;
import br.com.async.utils.SmartUtils;

@Repository
@Resource(name="studentRepositoryImpl")
public class StudentRepositoryImpl extends AbstractRepositoryImpl<Student, Integer> implements StudentRepository {

	public StudentRepositoryImpl(){
		super(Student.class);
	}
	
	@Override
	public List<Student> searchByCodeOrName(String search) {
		
		Criteria criteria = getSession().createCriteria(Student.class);
		
		Integer code = SmartUtils.StringToInteger(search);
		Criterion criterion;
		if(code != -1)
			criterion = Restrictions.eq("code", code );
		else{
			criteria.createAlias("person", "p");
			criterion = Restrictions.ilike("p.name", search, MatchMode.ANYWHERE);
		}
			    
		criteria.add(criterion);
		
		try {
			return criteria.list();
		} catch (RuntimeException re) {
			System.err.println(re);
			return null;
		}
	}
	
}
