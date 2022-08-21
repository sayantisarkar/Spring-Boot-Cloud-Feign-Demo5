package com.accenture.lkm.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.lkm.model.Employee;

@FeignClient(name="feign-employee-producer",decode404=true)
public interface MyFiegnClient {

	@RequestMapping(value = "/emp/controller/getDetails", method = RequestMethod.GET)
	ResponseEntity<List<Employee>> findAll();

	@RequestMapping(value = "/emp/controller/getDetailsById/{id}", method = RequestMethod.GET)
	ResponseEntity<Employee> findByEmployeeId(@PathVariable("id") Integer employeeId);
	
	@RequestMapping(value="/emp/controller/addEmp",method=RequestMethod.POST)
	ResponseEntity<String> addEmployee(@RequestBody Employee employee);
	
	@RequestMapping(value="/emp/controller/updateEmp",method=RequestMethod.PUT)
	ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee);
		
	@RequestMapping(value="/emp/controller/deleteEmp/{id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int myId);
}
