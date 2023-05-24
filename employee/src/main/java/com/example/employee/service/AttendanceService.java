package com.example.employee.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.BufferedReader;
import com.opencsv.CSVReader;
import java.io.InputStreamReader;

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
    	int employeeId = attendanceDTO.getEmployee().getId();
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

  public List<Attendance> processAttendance(MultipartFile file) throws IOException {
      List<Attendance> attendanceList = new ArrayList<>();

      BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
      CSVReader csvReader = new CSVReader(fileReader);
      String[] record;
      int line = 0;
      while ((record = csvReader.readNext()) != null) {
          if (line == 0) { 
              line++;
              continue;
          }
          try {
        	  int employeeId = Integer.parseInt(record[0].trim());
              int day = Integer.parseInt(record[1].trim());
              boolean isPresent = Integer.parseInt(record[2].trim()) == 1;


              Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
              if (employeeOptional.isPresent()) {
                  Employee employee = employeeOptional.get();

                  Attendance attendance = new Attendance();
                  attendance.setEmployee(employee);
                  attendance.setDay(day);
                  attendance.setPresent(isPresent);

                  attendanceList.add(attendance);
              } else {
                  
              }
          } catch (NumberFormatException e) {
              e.printStackTrace();
          }
          line++;
      }
      csvReader.close();
      fileReader.close();
      return attendanceList;
  }
public List<Attendance> getAttendanceReport(Department department, Integer day, Boolean isPresent, String city) {
	 List<Attendance> attendanceList = null;
		return attendanceList;
}
public List<Attendance> findByDayAndisPresent(
	      Integer day,Boolean isPresent) {
	List<Attendance> attendanceList = attendanceRepository.findByDayAndIsPresent(day, isPresent);
	return attendanceList;
}

public AttendanceRepository getAttendanceRepository() {
	return attendanceRepository;
}

}





