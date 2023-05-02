package com.epic_energies.business.service;

import java.util.Locale;

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
import com.github.javafaker.Faker;

@Service
public class CustomerService {
    @Autowired
    CustomerDAO customerRep;
    @Autowired
    OperativeAdressDAO opAddressRep;
    @Autowired
    LegalAdressDAO legAddressRep;
    
    private Faker fake = Faker.instance(new Locale("it-IT"));

    @Autowired
    @Qualifier("FakeCustomer")
    private ObjectProvider<Customer> customerProvider;

    @Autowired
    @Qualifier("FakeOperativeAdress")
    private ObjectProvider<OperativeAdress> opAddressProvider;

    @Autowired
    @Qualifier("FakeLegalAdress")
    private ObjectProvider<LegalAdress> legalAddressProvider;

    public void persistFakeCustomer() {
    	Customer c = customerProvider.getObject();
    	LegalAdress legalAdd = legalAddressProvider.getObject();
    	legAddressRep.save(legalAdd);
    	c.setLegalAdress(legalAdd);
    	if (fake.number().numberBetween(0, 10) > 7) {
    		OperativeAdress opAddress = opAddressProvider.getObject();
    		opAddressRep.save(opAddress);
    		c.setOperativeAdress(opAddress);
    	}
    	
    	customerRep.save(c);
    	
    }
    
    public void insertCustomer(Customer c) {
    		customerRep.save(c);
    }

    public void insertOperativeAdress(OperativeAdress opAdr) {
	opAddressRep.save(opAdr);
    }

    public void insertLegalAdress(LegalAdress legAdr) {
	legAddressRep.save(legAdr);
    }

}
