package com.epic_energies.business.configuration;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.model.InvoiceStatus;
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

    @Bean("FakeInvoice")
    @Scope("prototype")
    public Invoice fakeInvoice() {
	Invoice f = new Invoice();
	Faker fake = Faker.instance(new Locale("en-EN"));
	f.setYear(fake.number().numberBetween(2000, 2023));
	f.setDate(fake.date().birthday());
	f.setAmount(BigDecimal.valueOf(fake.number().randomDouble(2, 0, 1000)));
	f.setNumber(fake.number().randomDigitNotZero());
	int num = fake.number().numberBetween(1, 5);
	if (num == 1) {
	    f.setInvoice_status(InvoiceStatus.SENT);
	} else if (num == 2) {
	    f.setInvoice_status(InvoiceStatus.RECEIVED);
	} else if (num == 3) {
	    f.setInvoice_status(InvoiceStatus.EXPIRED);
	} else if (num == 4) {
	    f.setInvoice_status(InvoiceStatus.SUSPENDED);
	} else {
	    System.out.println("Errore durante la creazione della fattura!");
	}
	;
	return f;
    }
}
