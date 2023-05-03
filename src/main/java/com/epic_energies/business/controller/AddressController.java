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
import com.epic_energies.business.model.Address;
import com.epic_energies.business.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired AddressService addService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findAddressById(@PathVariable Long id) {
	return new ResponseEntity<Address>(addService.findAddressById(id), HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllAddress() {
	return new ResponseEntity<List<Address>>(addService.findAllAddress(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addNewAddress(@RequestBody Address a) {
		return new ResponseEntity<String>(addService.persistAddress(a), HttpStatus.CREATED);
	}
}