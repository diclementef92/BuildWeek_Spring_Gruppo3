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

	@Autowired private AddressDAO AddressRepo;

	public String persistAddress(Address a) {
		AddressRepo.save(a);
		return "Address correctly persisted on Database!";
	}

	public String updateAddress(Address a) {
		if (AddressRepo.existsById(a.getId())) {
			AddressRepo.save(a);
			return "Address correctly updated on Database!";
		} else {
			throw new EntityNotFoundException("Address with ID --> " + a.getId() + " doesn't exists on Database");
		}
	}

	public String deleteAddress(Address a) {
		if (AddressRepo.existsById(a.getId())) {
			AddressRepo.delete(a);
			return "Address correctly deleted from Database!";
		} else {
			throw new EntityNotFoundException("Address with ID --> " + a.getId() + " doesn't exists on Database");
		}
	}

	public String deleteAddress(Long id) {
		if (AddressRepo.existsById(id)) {
			AddressRepo.deleteById(id);
			;
			return "Address correctly deleted from Database!";
		} else {
			throw new EntityNotFoundException("Address with ID --> " + id + " doesn't exists on Database");
		}
	}

	public Address findAddressById(Long id) {
		if (AddressRepo.existsById(id)) {
			return AddressRepo.findById(id).get();
		} else {
			throw new EntityNotFoundException("Address with ID --> " + id + " doesn't exists on Database");
		}

	}
	public List<Address> findAllAddress() {
		return(List<Address>) AddressRepo.findAll();
	}

}