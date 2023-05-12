package com.example.employee.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Attendance;
import com.example.employee.model.Employee;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
@Autowired
	List<Attendance> findByDayAndIsPresent(Integer day,
			Boolean isPresent);

	Employee save(Employee employee);
}

