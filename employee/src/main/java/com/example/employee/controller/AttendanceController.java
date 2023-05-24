package com.example.employee.controller;
import java.util.Base64;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.employee.exception.UserNotFoundException;
import com.example.employee.model.Attendance;
import com.example.employee.model.Department;
import com.example.employee.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

  @Autowired
  private AttendanceService attendanceService;

  @PostMapping("/upload")
  public ResponseEntity<?> uploadAttendance(
      @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException, UserNotFoundException {

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

    if (!isValidUser(username, password)) {
        throw new UserNotFoundException("Invalid username or password");
    }

    List<Attendance> processedRecords = attendanceService.processAttendance(file);
    List<Long> processedIds = attendanceService.uploadAttendance(processedRecords);
    return ResponseEntity.ok(processedIds);
  }
  private boolean isValidUser(String username, String password) {
      
      return username.equals("root") && password.equals("password");
  }

  @GetMapping("/report")
  public ResponseEntity<?> getAttendanceReport(
      @RequestParam(name = "department") Department department,
      @RequestParam(name = "day") Integer day,
      @RequestParam(name = "isPresent") Boolean isPresent,
      @RequestParam(name = "city") String city,
      HttpServletRequest request) throws UserNotFoundException {

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

    if (!isValidUser(username, password)) {
        throw new UserNotFoundException("Invalid username or password");
    }

    List<?> reports = attendanceService.getAttendanceReport(department, day, isPresent, city);
    return ResponseEntity.ok(reports);
  }

}
