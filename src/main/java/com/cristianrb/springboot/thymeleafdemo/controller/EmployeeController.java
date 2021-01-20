package com.cristianrb.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cristianrb.springboot.thymeleafdemo.entity.Employee;
import com.cristianrb.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> employees = employeeService.findAll();
		theModel.addAttribute("employees", employees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// Create model attribute to bind form data
		Employee employee = new Employee();
		theModel.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id,
									Model theModel) {
		Employee employee = this.employeeService.findById(id);
		theModel.addAttribute("employee", employee);
		return "employees/employee-form";
		
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		this.employeeService.save(employee);
		
		// Use redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int id,
								Model theModel) {
		this.employeeService.deleteById(id);
		
		// Use redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	
	
}
