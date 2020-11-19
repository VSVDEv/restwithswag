package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name="REST Employee", description = "Employee controller")
public class EmployeeRestController {
	
	Logger log=LoggerFactory.getLogger(EmployeeRestController.class);

	private final EmployeeService service;
@Autowired
	public EmployeeRestController(EmployeeService service) {
		
		this.service = service;
	}
@Operation(summary="This is method wich return all employees from Db")
@ApiResponses(value= {
		@ApiResponse(responseCode = "200",
				description = "Fetched all the employees from DB",
				content = {@Content(mediaType = "application/json",
				schema = @Schema(implementation = Employee.class))}),
		@ApiResponse(responseCode = "404",description = "Not available",
		content = @Content)
})

@GetMapping("/employees")
public List<Employee> getEmployees(){
	List<Employee> employees = service.retrievereEmployees();
	log.debug("find employees");
	return employees;
	}
@Operation(summary="This is method wich return  employee from Db")
@ApiResponses(value= {
		@ApiResponse(responseCode = "200",
				description = "Fetched  the employee from DB",
				content = {@Content(mediaType = "application/json")}),
		@ApiResponse(responseCode = "404",description = "Not available",
		content = @Content)
})
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@Parameter(description = " id of employee to be searched")
			@PathVariable(name="employeeId")Long employeeId) {
	log.debug("find employee");
		return service.getEmployee(employeeId);
	}
@Operation(summary="This is method create  employee in Db")
@ApiResponses(value= {
		@ApiResponse(responseCode = "200",
				description = "Created  the employee in DB",
				content = {@Content(mediaType = "application/json")}),
		@ApiResponse(responseCode = "404",description = "Not available",
		content = @Content)
})
	@PostMapping("/employees")
@ResponseStatus(HttpStatus.CREATED)
	public void saveEmployee(@RequestBody Employee employee) {
		service.saveEmployee(employee);
		log.debug("Employee saved");
	}
@Operation(summary="This is method delete  employee from Db")
@ApiResponses(value= {
		@ApiResponse(responseCode = "200",
				description = "Deleted  the employee from DB",
				content = {@Content(mediaType = "application/json")}),
		@ApiResponse(responseCode = "404",description = "Not available",
		content = @Content)
})
	@DeleteMapping("/employees/{employeeId}")
	public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId) {
		service.deleteEmployee(employeeId);
		log.debug("Deleted");
		
	}
@Operation(summary="This is method update the  employee in Db")
@ApiResponses(value= {
		@ApiResponse(responseCode = "200",
				description = "Updated  the employee in DB",
				content = {@Content(mediaType = "application/json")}),
		@ApiResponse(responseCode = "404",description = "Not available",
		content = @Content)
})
	@PutMapping("/employees/{employeeId}")
	public void updateEmployee(@RequestBody Employee employee,
			@PathVariable(name="employeeId")Long employeeId) {
		Employee emp=service.getEmployee(employeeId);
		if(emp!=null) {
			service.updateEmployee(employee);
			log.debug("update employee");
		}
	}
}
