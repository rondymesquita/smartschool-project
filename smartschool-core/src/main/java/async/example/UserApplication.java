package async.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userApplication")
public class UserApplication{
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public boolean create(MyUser entity) {
		return userRepository.persist(entity);
	}

}
