package com.epic_energies.business.controller;

import java.math.BigDecimal;
import java.util.Date;

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

import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.model.InvoiceStatus;
import com.epic_energies.business.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    InvoiceService invService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findInvoiceById(@PathVariable Long id) {
	return new ResponseEntity<>(invService.FindInvoiceById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<?> findAllInvoices() {
	return new ResponseEntity<>(invService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity<?> findAllInvoicesPaged(Pageable pageable) {
	return new ResponseEntity<Page<Invoice>>(invService.findAll(pageable), HttpStatus.FOUND);
    }

    @GetMapping("/paged/bycustomer")
    public ResponseEntity<?> findAllInvoicesPagedByCustomer(@RequestBody Customer c, Pageable pageable) {
	return new ResponseEntity<Page<Invoice>>(invService.findAllByCustomer(c, pageable), HttpStatus.FOUND);
    }

    @GetMapping("/paged/bystatus")
    public ResponseEntity<?> findAllInvoicesPagedByStatus(@RequestBody InvoiceStatus status, Pageable pageable) {
	return new ResponseEntity<Page<Invoice>>(invService.findAllByStatus(status, pageable), HttpStatus.FOUND);
    }

    @GetMapping("/paged/bydate")
    public ResponseEntity<?> findAllInvoicesPagedByDate(@RequestBody Date date, Pageable pageable) {
	return new ResponseEntity<Page<Invoice>>(invService.findAllByDate(date, pageable), HttpStatus.FOUND);
    }

    @GetMapping("/paged/byyear")
    public ResponseEntity<?> findAllInvoicesPagedByYear(@RequestBody Integer year, Pageable pageable) {
	return new ResponseEntity<Page<Invoice>>(invService.findAllByYear(year, pageable), HttpStatus.FOUND);
    }

    @GetMapping("/paged/byamountrange/{amount1}&{amount2}")
    public ResponseEntity<?> findAllInvoicesPagedByAmountRange(@PathVariable BigDecimal amount1,
	    @PathVariable BigDecimal amount2, Pageable pageable) {
	return new ResponseEntity<Page<Invoice>>(invService.findAllByAmountRange(amount1, amount2, pageable),
		HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addNewInvoice(@RequestBody Invoice i) {
	return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@RequestBody String a) {
	return new ResponseEntity<>(HttpStatus.OK);
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
