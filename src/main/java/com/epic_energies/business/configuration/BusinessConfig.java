package com.epic_energies.business.configuration;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Fattura;
import com.epic_energies.business.model.StatoFattura;
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

    @Bean("FakeFattura")
    @Scope("prototype")
    public Fattura fakeFattura() {
	Fattura f = new Fattura();
	Faker fake = Faker.instance(new Locale("it-IT"));
	f.setAnno(fake.number().numberBetween(2000, 2023));
	f.setData(fake.date().birthday());
	f.setImporto(BigDecimal.valueOf(fake.number().randomDouble(2, 0, 1000)));
	f.setNumero(fake.number().randomDigitNotZero());
	int num = fake.number().numberBetween(1, 5);
	if (num == 1) {
	    f.setStato_fattura(StatoFattura.INVIATA);
	} else if (num == 2) {
	    f.setStato_fattura(StatoFattura.RICEVUTA);
	} else if (num == 3) {
	    f.setStato_fattura(StatoFattura.SCADUTA);
	} else if (num == 4) {
	    f.setStato_fattura(StatoFattura.SOSPESA);
	} else {
	    System.out.println("Errore durante la creazione della fattura!");
	}
	;
	return f;
    }
}
