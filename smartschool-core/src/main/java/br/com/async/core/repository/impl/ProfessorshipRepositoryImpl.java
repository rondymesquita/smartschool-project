package br.com.async.core.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.async.core.entities.Professorship;
import br.com.async.core.entities.Semester;
import br.com.async.core.repository.ProfessorshipRepository;
import br.com.async.utils.SmartUtils;

@Repository
@Resource(name="professorshipRepositoryImpl")
public class ProfessorshipRepositoryImpl  extends AbstractRepositoryImpl<Professorship, Integer> implements ProfessorshipRepository{

	public ProfessorshipRepositoryImpl(){
		super(Professorship.class);
	}
	
	@Override
	public List<Professorship> searchByCodeOrDisciplineOrProfessor(String search) {
		
		Criteria criteria = getSession().createCriteria(Professorship.class);
		
		Integer code = SmartUtils.StringToInteger(search);
		Criterion criterion, criterionDiscipline, criterionProfessor;
		if(code != -1){
			criterion = Restrictions.eq("code", code );
			criteria.add(criterion);
		}else{
			criteria.createAlias("discipline", "discipline");
			criteria.createAlias("professor", "professor");
			criteria.createAlias("professor.person", "person");
			
			criterionDiscipline = Restrictions.ilike("discipline.name", search, MatchMode.ANYWHERE);
			criterionProfessor = Restrictions.ilike("person.name", search, MatchMode.ANYWHERE);
			
			criteria.add(Restrictions.or(criterionDiscipline, criterionProfessor));
		}
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		try {
			return criteria.list();
		} catch (RuntimeException re) {
			System.err.println(re);
			return null;
		}
	}

	@Override
	public List<Semester> searchSemesterByCourse(String courseId) {
		Criteria criteria = getSession().createCriteria(Professorship.class);
		criteria.createAlias("course", "course");
		
		Integer code = SmartUtils.StringToInteger(courseId);
		Criterion criterion = Restrictions.eq("course.code", code );
		criteria.add(criterion);
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Professorship> professorhips = criteria.list();
		List<Semester> semesters = new ArrayList<Semester>();
		for (Professorship professorship : professorhips) {
			Semester semester = professorship.getSemester();
			if(!semesters.contains(semester))
				semesters.add(professorship.getSemester());
		}
		
		return semesters;
	}
	
	@Override
	public List<Professorship> searchProfessorshipsBySemester(String semesterId) {
		Criteria criteria = getSession().createCriteria(Professorship.class);
		criteria.createAlias("semester", "semester");
		
		Integer code = SmartUtils.StringToInteger(semesterId);
		Criterion criterion = Restrictions.eq("semester.code", code );
		criteria.add(criterion);
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Professorship> professorhips = criteria.list();
		
		return professorhips;
	}
	
	public List<Professorship> searchProfessorshipsByProfessorName(String professorName){
		Criteria criteria = getSession().createCriteria(Professorship.class);
		criteria.createAlias("professor", "professor");
		criteria.createAlias("professor.person", "person");
		
		Criterion criterion = Restrictions.eq("person.name", professorName );
		criteria.add(criterion);
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Professorship> professorhips = criteria.list();
		
		return professorhips;
	}
	
}
