package com.epic_energies.business.service;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epic_energies.business.model.Address;
import com.epic_energies.business.model.E_AddressType;
import com.epic_energies.business.model.Municipality;
import com.epic_energies.business.repository.AddressDAO;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressService {

    @Autowired
    private AddressDAO addressDao;
    @Autowired
    private MunicipalityService municipalityService;
	@Autowired
	@Qualifier("FakeLegAddress")
	private ObjectProvider<Address> legAddressProvider;

	@Autowired
	@Qualifier("FakeOpAddress")
	private ObjectProvider<Address> opAddressProvider;

	@Autowired private AddressDAO AddressRepo;

    public void createFakeAddress(Address a) {
	Faker fake = Faker.instance(new Locale("en-EN"));
	List<Municipality> list_municipality = municipalityService.findAllMunicipality();
	Integer random = fake.number().numberBetween(0, list_municipality.size() - 1);
	Municipality m = list_municipality.get(random);
	a.setMunicipality(m);
	addressDao.save(a);
    }

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
			switch (a.getAddressType()) {
			case LEGAL_ADDRESS -> throw new DataIntegrityViolationException("Customer must have at least one legal address assigned!");
			default -> {
				AddressRepo.delete(a);
				return "Address correctly deleted from Database!";
			}
			}
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

	public Page<Address> findAll(Pageable pageable) {
		return (Page<Address>) AddressRepo.findAll(pageable);
	}
	
	public Page<Address> findAllAddressByAddressType(E_AddressType type, Pageable pageable) {
		return (Page<Address>) AddressRepo.findByAddressType(type, pageable);
	}
}
