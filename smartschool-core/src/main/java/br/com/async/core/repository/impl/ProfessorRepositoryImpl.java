package br.com.async.core.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Professor;
import br.com.async.core.repository.ProfessorRepository;
import br.com.async.utils.SmartUtils;

@Repository
@Resource(name = "professorRepositoryImpl")
public class ProfessorRepositoryImpl extends AbstractRepositoryImpl<Professor, Integer> implements ProfessorRepository {

	public ProfessorRepositoryImpl() {
		super(Professor.class);
	}

	@Override
	public List<Professor> searchByCodeOrName(String search) {

		Criteria criteria = getSession().createCriteria(Professor.class);

		Integer code = SmartUtils.StringToInteger(search);
		Criterion criterion;
		if (code != -1)
			criterion = Restrictions.eq("code", code);
		else {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.async.core.repository.ProfessorRepository#findByEmail(java.lang
	 * .String)
	 */
	@Override
	public Professor findByEmail(String email) {
		Criteria criteria = getSession().createCriteria(Professor.class);
		criteria.createAlias("person", "p");
		Criterion criterion = Restrictions.eq("p.email", email);

		criteria.add(criterion);

		try {
			return (Professor) criteria.uniqueResult();
		} catch (RuntimeException re) {
			System.err.println(re);
			return null;
		}
	}

}
