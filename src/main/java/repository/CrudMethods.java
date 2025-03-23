package repository;

import java.util.List;
import java.util.Optional;

public interface CrudMethods<T,ID> {

	T save(T entity);
	T update(T entity);
	T delete(ID id);
	Optional<T> findById(ID id);
	List<T> findAll();
}
