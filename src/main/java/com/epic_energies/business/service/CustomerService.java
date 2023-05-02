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
    @Autowired
    CustomerDAO customerRep;
    @Autowired
    OperativeAdressDAO opAdressRep;
    @Autowired
    LegalAdressDAO legAdressRep;

    @Autowired
    @Qualifier("FakeCustomer")
    private ObjectProvider<Customer> customerProvider;

    @Autowired
    @Qualifier("FakeOperativeAdress")
    private ObjectProvider<OperativeAdress> opAdressProvider;

    @Autowired
    @Qualifier("FakeLegalAdress")
    private ObjectProvider<LegalAdress> legalAdressProvider;

    public void insertCustomer(Customer c) {
	customerRep.save(c);
    }

    public void insertOperativeAdress(OperativeAdress opAdr) {
	opAdressRep.save(opAdr);
    }

    public void insertLegalAdress(LegalAdress legAdr) {
	legAdressRep.save(legAdr);
    }

    public void createCustomer() {
	insertCustomer(customerProvider.getObject());
    }

    public void createoperAdress() {
	insertOperativeAdress(opAdressProvider.getObject());
    }

    public void createopAdress() {
	insertLegalAdress(legalAdressProvider.getObject());
    }
}
