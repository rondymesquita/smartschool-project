package async.example;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Transactional
@Repository("userDao")
public class UserRepository {

//	@Autowired
//    private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public UserRepository(){}
	
	public boolean persist(MyUser transientInstance) {
		try {
//			sessionFactory.getCurrentSession().persist(transientInstance);
			hibernateTemplate.persist(transientInstance);
			return true;
		} catch (RuntimeException re) {
			return false;
		}
	}

}
