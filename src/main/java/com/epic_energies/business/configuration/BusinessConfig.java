package com.epic_energies.business.configuration;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epic_energies.business.model.CostumerType;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.LegalAdress;
import com.epic_energies.business.model.OperativeAdress;
import com.epic_energies.business.repository.CustomerDAO;
import com.epic_energies.business.repository.LegalAdressDAO;
import com.epic_energies.business.repository.OperativeAdressDAO;
import com.github.javafaker.Faker;

@Configuration
public class BusinessConfig {
	
	@Autowired
	CustomerDAO CustDao;
	@Autowired
	OperativeAdressDAO opAdrDao;
	@Autowired
	LegalAdressDAO LegalAdrDao;
	
	@Bean("FakeOperativeAdress")
	@Scope("prototype")
	public OperativeAdress fakeOperativeAdress() {
		OperativeAdress opAdr = new OperativeAdress();
		Faker fake = Faker.instance(new Locale("en-EN"));
		opAdr.setStreetName(fake.address().streetAddress());
		opAdr.setStreetNumber(fake.number().numberBetween(1, 100));
		opAdr.setPlace(fake.address().cityName());
		opAdr.setPostCode(fake.number().numberBetween(10000, 100000));		
		return opAdr;
	}
	
	@Bean("fakeLegalAdress")
	@Scope("prototype")
	public LegalAdress fakeLegalAdress() {
		LegalAdress legAdr = new LegalAdress();
		Faker fake = Faker.instance(new Locale("en-EN"));
		legAdr.setStreetName(fake.address().streetAddress());
		legAdr.setStreetNumber(fake.number().numberBetween(1, 100));
		legAdr.setPlace(fake.address().cityName());
		legAdr.setPostCode(fake.number().numberBetween(10000, 100000));		
		return legAdr;
	}
	
	@Bean("FakeCustomer")
	@Scope("prototype")
	public Customer fakeCustomer() {
		Customer customer = new Customer();
		Faker fake = Faker.instance(new Locale("en-EN"));
		customer.setBusinessName(fake.company().name());
		customer.setVatNumber(fake.number().numberBetween(10000000001l, 100000000000l));
		customer.setContactName(fake.name().fullName());
		customer.setEmail(customer.getContactName() + "gmail.com");
		customer.setInsertData(LocalDate.of(2023, 5, (int) Math.floor(Math.random()*30+1)));
		customer.setLastContactData(LocalDate.of(2023, 5, (int) Math.floor(Math.random()*30+1)));
		customer.setPec(customer.getContactName() + "gmail.com");
		customer.setPhoneNumber(3490000000l + fake.number().numberBetween(1l, 10000000l));
		customer.setContactEmail(customer.getContactName() + "gmail.com");
		customer.setContactPhone(3490000000l + fake.number().numberBetween(1l, 10000000l));
		customer.setCostumerType(null);		
		int num = fake.number().numberBetween(1, 5);
		if (num == 1) {
			customer.setCostumerType(CostumerType.PA);
		} else if (num == 2) {
			customer.setCostumerType(CostumerType.SAS);
		} else if (num == 3) {
			customer.setCostumerType(CostumerType.SPA);
		}else if (num == 4) {
			customer.setCostumerType(CostumerType.SRL);
		} else {
		    System.out.println("No Costumer type supported");
		}
		customer.setOperativeAdress(fakeOperativeAdress());
		customer.setLegalAdress(fakeLegalAdress());		
		return customer;
	}
}
