package com.example.employee.controller;

import java.net.URI;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.exception.UserNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;
  @GetMapping
  public String employee() {
	  return "Hello!";
  }
  @PostMapping
  
  public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee, HttpServletRequest request) throws UserNotFoundException {

	    String authorizationHeader = request.getHeader("Authorization");
	    if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
	    	
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	    String encodedCredentials = authorizationHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decodedBytes);
        String[] parts = credentials.split(":", 2);
        String username = parts[0];
        String password = parts[1];
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
  
	    if (!isValidUser(username, password)) {
	        throw new UserNotFoundException("Invalid username or password");
	    }

	    Employee registeredEmployee = employeeService.registerEmployee(employee);
	    if (registeredEmployee == null) {
	    }
	    return ResponseEntity.created(URI.create("/api/employees/" + registeredEmployee.getId())).body(registeredEmployee);
	  }

	  private boolean isValidUser(String username, String password) {
	      
	      return username.equals("root") && password.equals("password");
	  }
	  
	  @PostMapping("/register")
	  public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) {
		  Employee registeredEmployee = employeeService.registerEmployee(employee);
		  if (registeredEmployee == null) {
		  }
		  return ResponseEntity.created(URI.create("/api/employees/" + registeredEmployee.getId())).body(registeredEmployee);
	  }
}