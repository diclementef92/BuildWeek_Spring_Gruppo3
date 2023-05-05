package com.epic_energies.business.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epic_energies.business.model.Address;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	CustomerService cusService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
		return new ResponseEntity<Customer>(cusService.findCustomerById(id), HttpStatus.FOUND);
	}

	@GetMapping
	public ResponseEntity<?> findAllCustomer() {
		return new ResponseEntity<List<Customer>>(cusService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/business-name-like/{name}")
	public ResponseEntity<?> findAllByBusinessNameLike(@PathVariable String name) {
		try {
			return new ResponseEntity<List<Customer>>(cusService.findAllByBusinessNameLike(name), HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/contact-name-like/{name}")
	public ResponseEntity<?> findAllByContactNameLike(@PathVariable String name) {
		try {
			return new ResponseEntity<List<Customer>>(cusService.findAllByContactNameLike(name),HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/all-customers-by-last-contact-data")
	public ResponseEntity<?> getAllCustomersOrderByLastContactData() {
		try {
			return new ResponseEntity<List<Customer>>(cusService.getAllCustomersOrderByLastContactData(),HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/all-customers-by-business-name")
	public ResponseEntity<?> getAllCustomersOrderByBusinessName() {
		try {
			return new ResponseEntity<List<Customer>>(cusService.getAllCustomersOrderByBusinessName(),HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/all-customers-by-annual-income")
	public ResponseEntity<?> getAllCustomersOrderByAnnualIncome() {
		try {
			return new ResponseEntity<List<Customer>>(cusService.getAllCustomersOrderByAnnualIncome(),HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/all-customers-by-insert-data")
	public ResponseEntity<?> getAllCustomersOrderByInsertData() {
		try {
			return new ResponseEntity<List<Customer>>(cusService.getAllCustomersOrderByInsertData(),HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCustomersByLegalAddress")
	public ResponseEntity<?> getAllCustomersByLegalAddress(@RequestBody Address address) {
		try {
			return new ResponseEntity<List<Customer>>(cusService.findAllByLegalAddress(address), HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCustomersByOperativeAddress")
	public ResponseEntity<?> getAllCustomersByOperativeAddress(@RequestBody Address address) {
		try {
			return new ResponseEntity<List<Customer>>(cusService.findAllByOperativeAddress(address), HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllByLegalAddressProvince")
	public ResponseEntity<?> findAllByLegalAddressProvince(@RequestBody String province) {
		try {
			return new ResponseEntity<List<Customer>>(cusService.findAllByLegalAddressProvince(province),
					HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllByOperativeAddressProvince")
	public ResponseEntity<?> findAllByOperativeAddressProvince(@RequestBody String province) {
		try {
			return new ResponseEntity<List<Customer>>(cusService.findAllByOperativeAddressProvince(province),
					HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/paged")
	public ResponseEntity<?> findAllCustomersPaged(Pageable pageable) {
		return new ResponseEntity<Page<Customer>>(cusService.findAll(pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/paged/by-name-like/{name}")
	public ResponseEntity<?> findAllCustomersByNameLikePaged(@PathVariable String name, Pageable pageable) {
		return new ResponseEntity<Page<Customer>>(cusService.findAllByBusinessNameLike(name, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/paged/income/{amount1}&{amount2}")
	public ResponseEntity<?> findCustomersByIncomeRangePaged(@PathVariable BigDecimal amount1, @PathVariable BigDecimal amount2, Pageable pageable) {
		return new ResponseEntity<Page<Customer>>(cusService.findCustomersByIncomeRange(amount1, amount2, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/paged/insertdata/{data1}&{data2}")
	public ResponseEntity<?> findCustomersByInsertDataPaged(@PathVariable LocalDate data1, @PathVariable LocalDate data2, Pageable pageable) {
		return new ResponseEntity<Page<Customer>>(cusService.findCustomersByInsertData(data1, data2, pageable), HttpStatus.FOUND);
	}
	
	@GetMapping("/paged/lastcontact/{data1}&{data2}")
	public ResponseEntity<?> findCustomersByLastContactDataPaged(@PathVariable LocalDate data1, @PathVariable LocalDate data2, Pageable pageable) {
		return new ResponseEntity<Page<Customer>>(cusService.findCustomersByLastContactData(data1, data2, pageable), HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<?> addNewCustomer(@RequestBody Customer c) {
		return new ResponseEntity<String>(cusService.persistCustomer(c), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer c) {
		return new ResponseEntity<String>(cusService.updateCustomer(c), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteCustomer(@RequestBody Customer c) {
		return new ResponseEntity<String>(cusService.deleteCustomer(c), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
		return new ResponseEntity<String>(cusService.deleteCustomer(id), HttpStatus.OK);
	}
}
