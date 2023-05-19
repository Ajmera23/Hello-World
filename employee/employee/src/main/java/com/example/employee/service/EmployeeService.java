package com.example.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.model.Attendance;
import com.example.employee.model.Employee;
import com.example.employee.repository.AttendanceRepository;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
  @Autowired
  private AttendanceRepository attendanceRepository;

  public List<Employee> findByDayAndIsPresent(Integer day, Boolean isPresent) {
    List<Attendance> attendanceList = attendanceRepository.findByDayAndIsPresent(day, isPresent);
    List<Employee> employeeList = attendanceList.stream()
      .map(attendance -> attendance.getEmployee())
      .collect(Collectors.toList());
    return employeeList;
  }

  @Autowired
  private EmployeeRepository employeeRepository;

  public Employee registerEmployee(Employee employee) {
    return employeeRepository.save(employee);	
  }
}