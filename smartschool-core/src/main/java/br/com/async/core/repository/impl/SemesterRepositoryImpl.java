package br.com.async.core.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Semester;
import br.com.async.core.repository.SemesterRepository;

/**
 * Created by rondymesquita on Jul 25, 2015
 *
 */
@Repository
@Resource(name="semesterRepositoryImpl")
public class SemesterRepositoryImpl extends AbstractRepositoryImpl<Semester, Integer> implements SemesterRepository{

	/**
	 * @param entity
	 */
	public SemesterRepositoryImpl() {
		super(Semester.class);
	}

	@Override
	public List<Semester> searchByCodeOrName(String search) {
		
		Criteria criteria = getSession().createCriteria(Semester.class);
		
		Criterion criterion;
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
