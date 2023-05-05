package com.epic_energies.business.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public List<Customer> findAllByBusinessNameLike(String businessName) throws NoSuchElementException {
		return customerRep.findAllByBusinessNameLike(businessName).get();
	}

	public List<Customer> findAllByContactNameLike(String contactName) throws NoSuchElementException {
		return customerRep.findAllByContactNameLike(contactName).get();
	}
	
	public List<Customer> getAllCustomersOrderByLastContactData() throws NoSuchElementException {
		return customerRep.getAllCustomersOrderByLastContactData().get();
	}

	public List<Customer> getAllCustomersOrderByBusinessName() throws NoSuchElementException {
		return customerRep.getAllCustomersOrderByBusinessName().get();
	}

	public List<Customer> getAllCustomersOrderByAnnualIncome() throws NoSuchElementException {
		return customerRep.getAllCustomersOrderByAnnualIncome().get();
	}

	public List<Customer> getAllCustomersOrderByInsertData() throws NoSuchElementException {
		return customerRep.getAllCustomersOrderByInsertData().get();
	}

	public List<Customer> findAllByLegalAddress(Address address) throws NoSuchElementException {
		return customerRep.findAllByLegalAddress(address).get();
	}

	public List<Customer> findAllByOperativeAddress(Address address) throws NoSuchElementException {
		return customerRep.findAllByOperativeAddress(address).get();
	}

	public List<Customer> findAllByLegalAddressProvince(String province) throws NoSuchElementException {
		return customerRep.findAllByLegalAddressProvince(province).get();
	}

	public List<Customer> findAllByOperativeAddressProvince(String province) throws NoSuchElementException {
		return customerRep.findAllByOperativeAddressProvince(province).get();
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

	// Pageable
    public Page<Customer> findAll(Pageable pageable) {
    	return (Page<Customer>) customerRep.findAll(pageable);
    }
    
    public Page<Customer> findAllByBusinessNameLike(String name, Pageable pageable) {
    	return (Page<Customer>) customerRep.findAllByBusinessNameLike(name, pageable);
    }
    
    public Page<Customer> findCustomersByIncomeRange(BigDecimal amount1, BigDecimal amount2, Pageable pageable) {
    	return (Page<Customer>) customerRep.findCustomersByIncomeRange(amount1, amount2, pageable);
    }
    
    public Page<Customer> findCustomersByInsertData(LocalDate data1, LocalDate data2, Pageable pageable) {
    	return (Page<Customer>) customerRep.findCustomerByInsertData(data1, data2, pageable);
    }
    
    public Page<Customer> findCustomersByLastContactData(LocalDate data1, LocalDate data2, Pageable pageable) {
    	return (Page<Customer>) customerRep.findCustomerByLastContactData(data1, data2, pageable);
    }

}
