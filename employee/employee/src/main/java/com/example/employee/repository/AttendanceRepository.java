package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository<Attendance> extends JpaRepository<Attendance, Long> {

	void save(com.example.employee.model.Attendance attendance);
  
}
