package br.com.async.application;

import org.springframework.stereotype.Component;

import br.com.async.core.entities.Professor;

@Component
public class ProfessorApplication implements AbstractApplication<Professor>{
	
//	@Autowired
//	private ProfessorRepositoryList ProfessorRepository;
//	
//	public Professor find(Integer id){
//		return ProfessorRepository.find(id);
//	}
//	
//	public List<Professor> list(){
//		return ProfessorRepository.list();
//	}
//
//	@Override
//	public void save(Professor entity) {
//		ProfessorRepository.save(entity);
//	}
//
//	@Override
//	public void update(Professor entity) {
//		ProfessorRepository.update(entity);
//	}
//
//	@Override
//	public void delete(Integer code) {
//		ProfessorRepository.delete(code);
//	}

}
