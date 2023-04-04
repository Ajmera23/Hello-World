package com.example.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Attendance;
import com.example.employee.model.Employee;
import com.example.employee.repository.AttendanceRepository;
@Repository
public class EmployeeService  {
@Autowired
public AttendanceRepository<Attendance> attendanceRepository;
private AttendanceRepository<Attendance> employeeRepository;
	public List<Employee> findByDayAndIsPresent(
		      Integer day,Boolean isPresent) {
		List<Attendance> attendanceList = attendanceRepository.findByDayAndIsPresent(day, isPresent);
		List<Employee> employeeList = attendanceList.stream()
				.map(attendance -> attendance.getEmployee())
				.collect(Collectors.toList());
		return employeeList;
	}
	public Employee registerEmployee(Employee employee) {
		 employeeRepository = null;
		return employeeRepository.save(employee);	
}
}