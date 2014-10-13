package async.example;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

//@Transactional
@Repository("userDao")
public class UserRepository {

//	@Autowired
//    private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public UserRepository(){}
	
	@Transactional
	public boolean save(MyUser transientInstance) {
		try {
//			sessionFactory.getCurrentSession().persist(transientInstance);
			hibernateTemplate.save(transientInstance);
			return true;
		} catch (RuntimeException re) {
			System.err.println(re);
			return false;
		}
	}

}
