package com.wmp.controller;

import com.wmp.model.Employee;
import com.wmp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Created by Garrett Kizior on 5/25/2018.
 */

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	// Create a new Employee
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		employee.setCreatedAt();
		return employeeRepository.save(employee);
	}

	// Update a Employee
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(employeeId);
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setAddress(employeeDetails.getAddress());
		employee.setCareerLevel(employeeDetails.getCareerLevel());
		employee.setCity(employeeDetails.getCity());
		employee.setDateOfBirth(employeeDetails.getDateOfBirth());
		employee.setDateOfJoin(employeeDetails.getDateOfJoin());
		employee.setState(employeeDetails.getState());
		employee.setZipcode(employeeDetails.getZipcode());
		return employeeRepository.save(employee);
	}

	// Delete a Employee
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/employees/{id}")
	public long deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId);
		employeeRepository.delete(employee);
		return employeeId;
	}
}