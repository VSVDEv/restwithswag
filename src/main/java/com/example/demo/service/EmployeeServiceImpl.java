package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository repository;
	
	
@Autowired
	public EmployeeServiceImpl(EmployeeRepository repository) {
		
		this.repository = repository;
	}

	@Override
	public List<Employee> retrievereEmployees() {
		List<Employee> employees=repository.findAll();
		return employees;
	}

	@Override
	public Employee getEmployee(Long employeeId) {
		Optional<Employee>op=repository.findById(employeeId);
		return op.get();
	}

	@Override
	public void saveEmployee(Employee employee) {
		repository.saveAndFlush(employee);
		
	}

	@Override
	public void deleteEmployee(Long employeeId) {
	repository.deleteById(employeeId);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		repository.saveAndFlush(employee);
		
	}

}
