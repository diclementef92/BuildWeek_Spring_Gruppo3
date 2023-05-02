package com.epic_energies.business.configuration;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epic_energies.business.model.CostumerType;
import com.epic_energies.business.model.Customer;
import com.github.javafaker.Faker;

@Configuration
public class BusinessConfig {
	@Bean
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
		return customer;
	}
}
