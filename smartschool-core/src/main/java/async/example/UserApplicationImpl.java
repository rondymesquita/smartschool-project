package async.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserApplicationImpl implements UserApplication{
	
	@Autowired
	@Qualifier("userRepository")
	private AbstractRepository<MyUser, Integer> repository;

	@Transactional
	public boolean save(MyUser entity) {
		return repository.save(entity);
	}

}
