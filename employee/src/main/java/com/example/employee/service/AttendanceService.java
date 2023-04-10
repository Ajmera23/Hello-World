package com.example.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.employee.model.Attendance;
import com.example.employee.model.Department;
import com.example.employee.model.Employee;
import com.example.employee.repository.AttendanceRepository;
import com.example.employee.repository.EmployeeRepository;
@Service
public class AttendanceService {

  private final EmployeeRepository employeeRepository;
  private final AttendanceRepository attendanceRepository;

  @Autowired
  public AttendanceService(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
    this.employeeRepository = employeeRepository;
    this.attendanceRepository = attendanceRepository;
  }
  public List<Long> uploadAttendance(List<Attendance> attendanceDTOList) {
    List<Long> processedIds = new ArrayList<>();
    List<Long> failedIds = new ArrayList<>();

    for (Attendance attendanceDTO : attendanceDTOList) {
      int employeeId = attendanceDTO.getId();
      Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

      if (employeeOptional.isPresent()) {
        Employee employee = employeeOptional.get();
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDay(attendanceDTO.getDay());
        attendance.setPresent(attendanceDTO.isPresent());
        attendanceRepository.save(attendance);
        processedIds.add((long) employeeId);
      } else {
        failedIds.add((long) employeeId);
      }
    }

    return processedIds;
  }

  public List<Attendance> processAttendance(MultipartFile file) {
    List<Attendance> attendance = null;
	return attendance;
  }

  public <AttendanceReport> List<AttendanceReport> getAttendanceReport(Department department, Integer day, Boolean isPresent, String city) {
  
    List<AttendanceReport> attendanceReport = null;
	return attendanceReport;
  }
  public List<Attendance> findByDayAndIsPresent(
	      Integer day,Boolean isPresent) {
	List<Attendance> attendanceList = attendanceRepository.findByDayAndIsPresent(day, isPresent);
	return attendanceList;
  }

public AttendanceRepository getAttendanceRepository() {
	return attendanceRepository;
}

}






