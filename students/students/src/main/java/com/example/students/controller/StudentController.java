package com.example.students.controller;
import com.example.students.model.Student;
import com.example.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
	@Autowired
    StudentRepository studentRepository;
	@RequestMapping(value="/students", method=RequestMethod.GET)
	@ResponseBody
	public List<Student> getAllStudents() {
	return studentRepository.findAll();
	}

	@RequestMapping(value="/student", method=RequestMethod.POST)
	@ResponseBody
	public Student addStudent(Student student) {
	return studentRepository.save(student);
	}

	@RequestMapping(value="/findstudent", method = RequestMethod.GET)
	@ResponseBody
	public Optional<Student> findStudent(@RequestParam("id") int id) {
	return studentRepository.findById(id);
	}

	@RequestMapping(value= "/updatestudent", method = RequestMethod.GET)
	@ResponseBody
	public Student updateStudent(@RequestBody Student student){
	return studentRepository.save(student);
	}

	@RequestMapping(value="/deletestudent", method = RequestMethod.GET)
	@ResponseBody
	public int deleteStudent(@RequestParam("id") int id) {
	return studentRepository.deleteByStudentId(id);
	}
}
