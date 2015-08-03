package br.com.async.core.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Course;
import br.com.async.core.entities.Discipline;
import br.com.async.core.repository.CourseRepository;
import br.com.async.utils.SmartUtils;

/**
 * Created by rondymesquita on Jul 25, 2015
 *
 */
@Repository
@Resource(name="courseRepositoryImpl")
public class CourseRepositoryImpl extends AbstractRepositoryImpl<Course, Integer> implements CourseRepository{

	/**
	 * @param entity
	 */
	public CourseRepositoryImpl() {
		super(Course.class);
	}

	@Override
	public List<Course> searchByCodeOrName(String search) {
		
		Criteria criteria = getSession().createCriteria(Course.class);
		
		Integer code = SmartUtils.StringToInteger(search);
		Criterion criterion;
		if(code != -1)
			criterion = Restrictions.eq("code", code );
		else	
			criterion = Restrictions.ilike("name", search, MatchMode.ANYWHERE);
			    
		criteria.add(criterion);
		
		try {
			return criteria.list();
		} catch (RuntimeException re) {
			System.err.println(re);
			return null;
		}
	}

}
