package com.college.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.portal.model.Student;
public interface StudentRepository extends JpaRepository<Student,Long>{

	List<Student> findByNameContainingIgnoreCase(String keyword);

}
