package com.epic_energies.business.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.LegalAdress;
import com.epic_energies.business.model.OperativeAdress;
import com.epic_energies.business.repository.CustomerDAO;
import com.epic_energies.business.repository.LegalAdressDAO;
import com.epic_energies.business.repository.OperativeAdressDAO;

@Service
public class CustomerService {
	@Autowired CustomerDAO customerRep;
	@Autowired OperativeAdressDAO opAdressRep;
	@Autowired LegalAdressDAO legAdressRep;
	
	@Autowired
	@Qualifier("FakeCustomer")
	private ObjectProvider<Customer> customerProvider;
	
	@Autowired
	@Qualifier("FakeOperativeAdress")
	private ObjectProvider<OperativeAdress> opAdressProvider;
	
	@Autowired
	@Qualifier("fakeLegalAdress")
	private ObjectProvider<LegalAdress> LegalAdressProvider;
	
	public Customer insertCustomer(Customer c) {
		customerRep.save(c);
		return c;
	}
	
	public OperativeAdress insertOperativeAdress(OperativeAdress opAdr) {
		opAdressRep.save(opAdr);
		return opAdr;
	}
	
	public LegalAdress insertLegalAdress(LegalAdress legAdr) {
		legAdressRep.save(legAdr);
		return legAdr;
	}
		
	public Customer createCustomer() {
		return insertCustomer(customerProvider.getObject());
	}
	
	public OperativeAdress createoperAdress() {
		return insertOperativeAdress(opAdressProvider.getObject());
	}
	
	public LegalAdress createopAdress() {
		return insertLegalAdress(LegalAdressProvider.getObject());
	}
}
