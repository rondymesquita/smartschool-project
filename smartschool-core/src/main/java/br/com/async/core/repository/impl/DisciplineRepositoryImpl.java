package br.com.async.core.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Discipline;
import br.com.async.core.repository.DisciplineRepository;
import br.com.async.utils.SmartUtils;

/**
 * Created by rondymesquita on 11/27/14.
 */
@Repository
@Resource(name="disciplineRepositoryImpl")
public class DisciplineRepositoryImpl  extends AbstractRepositoryImpl<Discipline, Integer> implements DisciplineRepository {

    public DisciplineRepositoryImpl() {
        super(Discipline.class);
    }
    
	@Override
	public List<Discipline> searchByCodeOrName(String search) {
		
		Criteria criteria = getSession().createCriteria(Discipline.class);
		
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
