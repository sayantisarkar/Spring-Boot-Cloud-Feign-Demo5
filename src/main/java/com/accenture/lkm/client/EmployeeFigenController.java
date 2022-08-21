package com.accenture.lkm.client;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.model.Employee;

@RestController
public class EmployeeFigenController {
	
	@Autowired
	private MyFiegnClient feignClient;

	@RequestMapping(value = "emp/controller/getEmployees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getEmployees(){	
		return feignClient.findAll();
	}
	
	@RequestMapping(value = "emp/controller/getEmployeeById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")int id){	
		return feignClient.findByEmployeeId(id);
	}
	
	@RequestMapping(value="/emp/controller/addEmployee",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
		return feignClient.addEmployee(employee);
	}
	
	@RequestMapping(value="/emp/controller/updateEmployee",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		return feignClient.updateEmployee(employee);
	}
	@RequestMapping(value="/emp/controller/deleteEmployee/{id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int myId){
		return feignClient.deleteEmployee(myId);
	}
	@ExceptionHandler(value=RuntimeException.class)
	public ResponseEntity<String> handleException(RuntimeException exception){
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}