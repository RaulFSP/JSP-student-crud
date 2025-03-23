package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Student;
import repository.StudentRepository;

@ApplicationScoped
public class StudentService {

	@Inject
	private StudentRepository repository;

	public void save(Student student) {
		student.setEmail(student.getEmail().toLowerCase());
		repository.save(student);

	}

	public void update(Student student) {
		repository.update(student);
	}
	
}
