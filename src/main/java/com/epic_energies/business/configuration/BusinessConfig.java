package com.epic_energies.business.configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epic_energies.business.model.Address;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.E_AddressType;
import com.epic_energies.business.model.E_CustomerType;
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.model.InvoiceStatus;
import com.epic_energies.business.repository.AddressDAO;
import com.github.javafaker.Faker;

@Configuration
public class BusinessConfig {

    @Autowired
    AddressDAO opertiveAddressDao;
    private Faker fake = Faker.instance(new Locale("en-EN"));

    @Bean("FakeLegAddress")
    @Scope("prototype")
    public Address fakeLegAddress() {

	return Address.builder().streetName(fake.address().streetAddress())
		.streetNumber(fake.number().numberBetween(1, 100)).place(fake.address().cityName())
		.postCode(fake.number().numberBetween(10000, 100000)).addressType(E_AddressType.LEGAL_ADDRESS).build();
    }

    @Bean("FakeOpAddress")
    @Scope("prototype")
    public Address fakeOpAddress() {

	return Address.builder().streetName(fake.address().streetAddress())
		.streetNumber(fake.number().numberBetween(1, 100)).place(fake.address().cityName())
		.postCode(fake.number().numberBetween(10000, 100000)).addressType(E_AddressType.OPERATIVE_ADDRESS)
		.build();
    }

    @Bean("FakeCustomer")
    @Scope("prototype")
    public Customer fakeCustomer() {

	String companyName = fake.company().name();
	String[] companyArray = companyName.split(" ");
	String companyEmail = "info_" + "@" + companyArray[0] + ".com";
	String fName = fake.name().firstName();
	String lName = fake.name().lastName();
	String contactEmail = fName.substring(0, 1) + "." + lName + "@example.com";
	int randomType = fake.number().numberBetween(0, 3);
	E_CustomerType companyType = getRandomCustomeType(randomType);
	Integer annualIncome = getRandomIncome(companyType);
	Date from = new Date(100, 0, 1); // aggiunge 1900 all'anno
	Date to = new Date();
	Date insertDate = fake.date().between(from, to);
	String pec;
	if (companyArray.length > 1) {
	    pec = companyArray[0] + "." + companyArray[1] + "@pec.it";
	} else {
	    pec = companyArray[0] + "@pec.it";
	}

	return Customer.builder().businessName(companyName).email(companyEmail).pec(pec)
		.vatNumber(fake.number().numberBetween(10000000001l, 100000000000l))
		.phoneNumber(3490000000l + fake.number().numberBetween(1l, 10000000l))
		.insertData(LocalDate.of(insertDate.getYear() + 1900, insertDate.getMonth() + 1, insertDate.getDate()))
		.lastContactData(LocalDate.of(insertDate.getYear() + 1900 + fake.number().numberBetween(1, 5), insertDate.getMonth() + 1, insertDate.getDate()))
		.contactName(fName + " " + lName).contactEmail(contactEmail)
		.contactPhone(3490000000l + fake.number().numberBetween(1l, 10000000l)).costumerType(companyType)
		.annualIncome(annualIncome).build();
    }

    private E_CustomerType getRandomCustomeType(int random) {

	E_CustomerType type = null;
	switch (random) {
	case 0 -> type = E_CustomerType.PA;
	case 1 -> type = E_CustomerType.SAS;
	case 2 -> type = E_CustomerType.SPA;
	case 3 -> type = E_CustomerType.SRL;
	}
	return type;
    }

    public Integer getRandomIncome(E_CustomerType cusType) {

	Integer income = null;
	switch (cusType) {
	case PA -> income = fake.number().numberBetween(50000, 250000);
	case SAS -> income = fake.number().numberBetween(250000, 1000000);
	case SPA -> income = fake.number().numberBetween(1000000, 50000000);
	case SRL -> income = fake.number().numberBetween(1000000, 50000000);
	}
	return income;
    }

    @Bean("FakeInvoice")
    @Scope("prototype")
    public Invoice fakeInvoice() {
	Invoice f = new Invoice();
	Faker fake = Faker.instance(new Locale("en-EN"));
	Date from = new Date(100, 0, 1); // aggiunge 1900 all'anno
	Date to = new Date();
	Date date = fake.date().between(from, to);
	f.setYear(date.getYear() + 1900);
	f.setDate(LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate()));
	f.setAmount(BigDecimal.valueOf(fake.number().randomDouble(2, 0, 1000)));
	f.setNumber(fake.number().randomDigitNotZero());
	int num = fake.number().numberBetween(1, 5);
	if (num == 1) {
	    f.setInvoiceStatus(InvoiceStatus.SENT);
	} else if (num == 2) {
	    f.setInvoiceStatus(InvoiceStatus.RECEIVED);
	} else if (num == 3) {
	    f.setInvoiceStatus(InvoiceStatus.EXPIRED);
	} else if (num == 4) {
	    f.setInvoiceStatus(InvoiceStatus.SUSPENDED);
	} else {
	    System.out.println("Errore durante la creazione della fattura!");
	}
	;

	return f;
    }
}
