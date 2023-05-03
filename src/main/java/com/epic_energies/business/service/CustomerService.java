package com.epic_energies.business.service;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Address;
import com.epic_energies.business.repository.CustomerDAO;
import com.epic_energies.business.repository.AddressDAO;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {
    @Autowired private CustomerDAO customerRep;
    @Autowired private AddressDAO AddressRep;

    @Autowired @Qualifier("FakeCustomer")
    private ObjectProvider<Customer> customerProvider;

    @Autowired @Qualifier("FakeOpAddress")
    private ObjectProvider<Address> opAddressProvider;

    @Autowired @Qualifier("FakeLegAddress")
    private ObjectProvider<Address> legalAddressProvider;

    private Faker fake = Faker.instance(new Locale("it-IT"));
    
    public void persistFakeCustomer() {
    	Customer c = customerProvider.getObject();
    	Address lAddress = legalAddressProvider.getObject();
    	AddressRep.save(lAddress);
    	c.setLegalAddress(lAddress);
    	if (fake.number().numberBetween(0, 10) > 7) {
    		Address opAddress = opAddressProvider.getObject();
    		AddressRep.save(opAddress);
    		c.setOperativeAddress(opAddress);
    	}
    	customerRep.save(c);
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
}
