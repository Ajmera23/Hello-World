package com.example.employee.repository;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Employee;
@Repository
public interface AttendanceRepository<Attendance> extends JpaRepository<Attendance, Integer> {

	List<Attendance> findByDayAndIsPresent(Integer day,
			Boolean isPresent);

	Employee save(Employee employee);
}