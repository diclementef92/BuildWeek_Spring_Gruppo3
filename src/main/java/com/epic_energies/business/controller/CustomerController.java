package com.epic_energies.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired CustomerService cusService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
		return new ResponseEntity<Customer>(cusService.findCustomerById(id), HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllCustomer() {
		return new ResponseEntity<List<Customer>>(cusService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addNewCustomer(@RequestBody Customer c) {
		return new ResponseEntity<String>(cusService.persistCustomer(c), HttpStatus.CREATED);
	}
	
	
}
