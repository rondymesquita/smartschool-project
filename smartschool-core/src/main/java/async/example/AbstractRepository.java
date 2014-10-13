package async.example;

public interface AbstractRepository<T, E> {

	boolean save(T entity);
	
}
