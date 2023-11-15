package com.example.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.company.model.Employees;

public interface EmployeesRepository 
  extends JpaRepository<Employees, Integer> {

}
