package com.example.employee.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.employee.model.Attendance;
import com.example.employee.model.Department;
import com.example.employee.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

  @Autowired
  private AttendanceService attendanceService;

  @PostMapping("/upload")
  public ResponseEntity<?> uploadAttendance(@RequestParam("file") MultipartFile file) throws IOException {
    List<Attendance> processedRecords = attendanceService.processAttendance(file);
      return ResponseEntity.ok(processedRecords);
  }

  @GetMapping("/report")
  public ResponseEntity<?> getAttendanceReport(
      @RequestParam(name = "department") Department department,
      @RequestParam(name = "day") Integer day,
      @RequestParam(name = "isPresent") Boolean isPresent,
      @RequestParam(name = "city") String city) {

    List<?> reports = attendanceService.getAttendanceReport(department, day, isPresent, city);
    return ResponseEntity.ok(reports);
  }
}


