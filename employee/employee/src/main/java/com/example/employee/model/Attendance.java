package com.example.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Attendance")
public class Attendance {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @ManyToOne
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private Employee employee;

  @Column(name = "day")
  private int day;

  @Column(name = "isPresent")
  private boolean isPresent;
 

  public Attendance() {}

  public Attendance(int id, int day, boolean isPresent) {
    this.id = id;
    this.day = day;
    this.isPresent = isPresent;

  }

public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public boolean isPresent() {
    return isPresent;
  }

  public void setPresent(boolean isPresent) {
    this.isPresent = isPresent;
  }



}

