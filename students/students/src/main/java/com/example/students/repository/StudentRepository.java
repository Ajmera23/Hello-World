package com.example.students.repository;

import com.example.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
List<Student> findAll();
Student findByStudentId(int id);
int deleteByStudentId(int id);

}

