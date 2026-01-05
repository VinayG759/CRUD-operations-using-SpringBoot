package com.college.portal.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.college.portal.model.Student;
import com.college.portal.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository repo;
	
	public StudentService(StudentRepository repo) {
		this.repo=repo;
	}
	
	public List<Student> getAllStudents(){
		return repo.findAll();
	}
	
	public void saveStudent(Student student) {
		repo.save(student);
	}
}
