package com.cristianrb.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianrb.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// No need to write any more code. 
	// Methods by default:
	// findAll()
	// findById(...)
	// save(...)
	// deleteById(...)
	
	// add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
}
