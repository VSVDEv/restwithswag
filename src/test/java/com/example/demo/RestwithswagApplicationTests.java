package com.example.demo;


import static org.junit.Assert.assertEquals;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.service.EmployeeServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
class RestwithswagApplicationTests {
@Autowired
	private EmployeeServiceImpl service;
	@MockBean
	private EmployeeRepository repository;
	@Test
	public void getEmployeesTest() {
		when(repository.findAll()).thenReturn(
				Stream.of(new Employee(1L,"Joe",1000,"home"),
						new Employee(2L,"John",2000,"devs"),
						new Employee(3L,"Will",2500,"manager"))
						.collect(Collectors.toList()));
		
		assertEquals(3,service.retrievereEmployees().size());
	}
	@Test
	public void getEmployeeTest() {
		Long id=2L;
		when(repository.findById(id)).thenReturn(
				Optional.of(
				new Employee(2L,"John",2000,"devs")));
		assertEquals(2000, service.getEmployee(id).getSalary());
	}
	@Test
	public void saveEmployeetest() {
		
		Employee employee = new Employee(3L,"Will",2500,"manager");
		service.saveEmployee(employee);
		verify(repository).saveAndFlush(employee);
	}
	@Test
	public void deleteEmployeeTest() {
		Long id=2L;
		service.deleteEmployee(id);
		verify(repository,times(1)).deleteById(id);
	}
	

	@Test
	void contextLoads() {
	}

}
