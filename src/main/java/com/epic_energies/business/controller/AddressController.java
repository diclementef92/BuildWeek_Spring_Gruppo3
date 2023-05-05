package com.epic_energies.business.controller;

import java.util.List;

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
import com.epic_energies.business.model.E_AddressType;
import com.epic_energies.business.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressService addService;

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

	@PutMapping("/{id}")
	public ResponseEntity<?> updateAddress(@RequestBody Address a) {
		return new ResponseEntity<String>(addService.updateAddress(a), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAddress(@RequestBody Address a) {
		return new ResponseEntity<String>(addService.deleteAddress(a), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
		return new ResponseEntity<String>(addService.deleteAddress(id), HttpStatus.OK);
	}
	
	@GetMapping("/paged")
	public ResponseEntity<?> findAllAddressPaged(Pageable pageable) {
		return new ResponseEntity<Page<Address>>(addService.findAll(pageable), HttpStatus.FOUND);
	}

	@GetMapping("/paged/by-address-type/{type}")
	public ResponseEntity<?> findAllAddressByAddressTypePaged(@PathVariable E_AddressType type, Pageable pageable) {
		return new ResponseEntity<Page<Address>>(addService.findAllAddressByAddressType(type, pageable), HttpStatus.FOUND);
	}


}