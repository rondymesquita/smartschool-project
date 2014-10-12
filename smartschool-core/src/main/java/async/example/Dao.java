package async.example;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Dao<E> {

	private final E entity;
	
	@Autowired
	SessionFactory sessionFactory;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Dao() {
		this.entity = null;
	}

	public Dao(E entity) {
		this.entity = entity;
	}

	@SuppressWarnings("unchecked")
	public Dao(Class<?> classe) {
		this.entity = (E) classe;
	}

	public E getEntity() {
		return this.entity;
	}
	
	@Transactional
	public boolean persist(E transientInstance) {
		try {
			getCurrentSession().persist(transientInstance);
			return true;
		} catch (RuntimeException re) {
			return false;
		}
	}
}