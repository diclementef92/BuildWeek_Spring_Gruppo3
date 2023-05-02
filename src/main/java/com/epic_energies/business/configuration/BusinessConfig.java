package com.epic_energies.business.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epic_energies.business.model.Customer;
import com.github.javafaker.Faker;

@Configuration
public class BusinessConfig {
	@Bean
	@Scope("prototype")
	public Customer fakeCustomer() {
		Customer customer = new Customer();
		Faker fake = Faker.instance(new Locale("en-EN"));
		customer.setBusinessName(null);
		customer.setVatNumber(null);
		customer.setEmail(null);
		customer.setInsertData(null);
		customer.setLastContactData(null);
		customer.setPec(null);
		customer.setPhoneNumber(null);
		customer.setContactEmail(null);
		customer.setContactName(null);
		customer.setContactPhone(null);
		
		return customer;
	}
}
