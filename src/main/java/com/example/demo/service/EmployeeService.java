package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	public List<Employee> retrievereEmployees();
	public Employee getEmployee(Long employeeId);
	
	public void saveEmployee(Employee employee);
	public void deleteEmployee(Long employeeId);
	public void updateEmployee(Employee employee);

}
