package repository;

import jakarta.enterprise.context.ApplicationScoped;
import model.Student;

@ApplicationScoped
public class StudentRepository extends CrudMethodsImpl<Student, Long>{

	public StudentRepository() {
		super(Student.class);
	}

}
