package com.epic_energies.business.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.epic_energies.business.model.Address;
import com.epic_energies.business.repository.AddressDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressService {

	@Autowired
	@Qualifier("FakeLegAddress")
	private ObjectProvider<Address> legAddressProvider;

	@Autowired
	@Qualifier("FakeOpAddress")
	private ObjectProvider<Address> opAddressProvider;

	@Autowired
	AddressDAO AddressRepo;

	public String persistAddress(Address a) {
		AddressRepo.save(a);
		return "Address salvato correttamente.";
	}

	public String updateAddress(Address a) {
		if (AddressRepo.existsById(a.getId())) {
			AddressRepo.save(a);
			return "Address salvato correttamente.";
		} else {
			return "Address non trovato!";
		}
	}

	public String deleteAddress(Address a) {
		if (AddressRepo.existsById(a.getId())) {
			AddressRepo.delete(a);
			return "Address eliminato correttamente.";
		} else {
			return "Address non trovato!";
		}
	}

	public String deleteAddress(Long id) {
		if (AddressRepo.existsById(id)) {
			AddressRepo.deleteById(id);
			;
			return "Address eliminato correttamente.";
		} else {
			return "Address non trovato!";
		}
	}

	public Address findAddressById(Long id) {
		if (AddressRepo.existsById(id)) {
			return AddressRepo.findById(id).get();
		} else {
			throw new EntityNotFoundException("Address non trovato!");
		}

	}
	public List<Address> findAllAddress() {
		return(List<Address>) AddressRepo.findAll();
	}

}