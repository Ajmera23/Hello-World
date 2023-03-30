package com.example.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.students.model.Student;
import com.example.students.repository.StudentRepository;

@Service
@Transactional
public class StudentService {

@Autowired
StudentRepository studentRepository;
public List<Student> getAllStudents() {
List<Student> students = studentRepository.findAll();
return students;
}

public Student getStudentByStudentId(int id) {
return studentRepository.findByStudentId(id);
}

public void saveStudent(Student student) {
try{
studentRepository.save(student);
}
catch(Exception e){
e.printStackTrace();
}
}
public void deleteStudent(int id) {
try{
studentRepository.deleteByStudentId(id);
}catch(Exception e){
e.printStackTrace();
}
}
}
