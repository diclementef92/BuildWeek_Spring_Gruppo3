package com.epic_energies.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.service.InvoiceService;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired InvoiceService invService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findInvoiceById(@PathVariable Long id) {
	return new ResponseEntity<>(invService.FindInvoiceById(id), HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllInvoices() {
	return new ResponseEntity<>(invService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addNewInvoice(@RequestBody Invoice i) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateInvoice(@RequestBody String a) {
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteInvoice(@RequestBody Invoice i) {
		return new ResponseEntity<>(invService.deleteInvoice(i), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
		return new ResponseEntity<>(invService.deleteInvoice(id), HttpStatus.OK);
	}
	
}
