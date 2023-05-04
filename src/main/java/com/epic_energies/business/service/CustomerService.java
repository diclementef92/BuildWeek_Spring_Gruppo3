package com.epic_energies.business.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.epic_energies.business.model.Address;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.repository.AddressDAO;
import com.epic_energies.business.repository.CustomerDAO;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAO customerRep;
    @Autowired
    private AddressDAO AddressRep;
    @Autowired
    private AddressService addressService;

    @Autowired
    @Qualifier("FakeCustomer")
    private ObjectProvider<Customer> customerProvider;

    @Autowired
    @Qualifier("FakeOpAddress")
    private ObjectProvider<Address> opAddressProvider;

    @Autowired
    @Qualifier("FakeLegAddress")
    private ObjectProvider<Address> legalAddressProvider;

    private Faker fake = Faker.instance(new Locale("it-IT"));

    public void persistFakeCustomer() {
	Customer c = customerProvider.getObject();
	Address lAddress = legalAddressProvider.getObject();
	addressService.createFakeAddress(lAddress);
	c.setLegalAddress(lAddress);
	if (fake.number().numberBetween(0, 10) > 7) {
	    Address opAddress = opAddressProvider.getObject();
	    addressService.createFakeAddress(opAddress);
	    c.setOperativeAddress(opAddress);
	}

	customerRep.save(c);
    }

    public Optional<List<Customer>> getAllCustomersOrderByBusinessName() {
	return customerRep.getAllCustomersOrderByBusinessName();
    }

    public Optional<List<Customer>> getAllCustomersOrderByAnnualIncome() {
	return customerRep.getAllCustomersOrderByAnnualIncome();
    }

    public Optional<List<Customer>> getAllCustomersOrderByInsertData() {
	return customerRep.getAllCustomersOrderByInsertData();
    }

    public Optional<List<Customer>> getAllCustomersOrderByLastContactData() {
	return customerRep.getAllCustomersOrderByLastContactData();
    }

	public Optional<List<Customer>> findAllByBusinessNameLike(String businessName) {
		return customerRep.findAllByBusinessNameLike(businessName);
	}

	public Optional<List<Customer>> findAllByContactNameLike(String contactName) {
		return customerRep.findAllByContactNameLike(contactName);
	}

    public String persistCustomer(Customer c) {
    	customerRep.save(c);
    	return "Customer correctly persisted on Database!";
    }
    
    public String updateCustomer(Customer c) {
    	if (customerRep.existsById(c.getId())) {
    		customerRep.save(c);
    		return "Customer correctly updated on Database!";
    	} else {
    		throw new EntityNotFoundException("Customer with ID --> " + c.getId() + " doesn't exists on Database!");
    	}
    }
    
    public String deleteCustomer(Customer c) {
    	if (customerRep.existsById(c.getId())) {
    		customerRep.delete(c);
    		return "Customer correctly deleted from Database!";
    	} else {
    		throw new EntityNotFoundException("Customer with ID --> " + c.getId() + " doesn't exists on Database!");
    	}
    }

    public String deleteCustomer(Long id) {
    	if (customerRep.existsById(id)) {
    		customerRep.deleteById(id);
    		return "Customer correctly deleted from Database!";
    	} else {
    		throw new EntityNotFoundException("Customer with ID --> " + id + " doesn't exists on Database!");
    	}
    }
    
    public Customer findCustomerById(Long id) {
    	if (customerRep.existsById(id)) {
    		return customerRep.findById(id).get();
    	} else {
    		throw new EntityNotFoundException("Customer with ID --> " + id + " doesn't exists on Database!");
    	}
    }
    
    public List<Customer> findAll() {
    	return (List<Customer>) customerRep.findAll();
    }
    
    public Page<Customer> findAll(Pageable pageable) {
    	return (Page<Customer>) customerRep.findAll(pageable);
    }

    public Page<Customer> findAllByBusinessName(String businessName, Pageable pageable) {
    	return (Page<Customer>) customerRep.findAllByBusinessName(businessName, pageable);
    }
}
