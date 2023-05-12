package com.example.employee.controller;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.employee.exception.UserNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;


	  @PostMapping
	  public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) throws UserNotFoundException {
		  Employee registeredEmployee = employeeService.registerEmployee(employee);
		  if (registeredEmployee == null) {
		      throw new UserNotFoundException("Failed to register employee");
		  }
		  return ResponseEntity.created(URI.create("/api/employees/" + registeredEmployee.getId())).body(registeredEmployee);
	  }
}
