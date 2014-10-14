package br.com.async.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import br.com.async.entities.MyUser;
import br.com.async.repository.impl.AbstractRepositoryImpl;

@Repository
@Resource(name="userRepository")
public class UserRepository extends AbstractRepositoryImpl<MyUser>{
	
	public UserRepository(){
		super(MyUser.class);
	}
	
//	@Transactional
//	public boolean save(MyUser transientInstance) {
//		try {
//			hibernateTemplate.save(transientInstance);
//			return true;
//		} catch (RuntimeException re) {
//			System.err.println(re);
//			return false;
//		}
//	}

}
