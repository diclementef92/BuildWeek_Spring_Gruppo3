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
import com.epic_energies.business.repository.LegalAdressDAO;
import com.epic_energies.business.repository.OperativeAdressDAO;
import com.github.javafaker.Faker;

@Configuration
public class BusinessConfig {

    @Autowired LegalAdressDAO legalAdressDao;
    @Autowired OperativeAdressDAO opertiveAddressDao;

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

    @Bean("FakeLegalAdress")
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
	String companyName = fake.company().name();
	String[] companyArray = companyName.split(" ");
	String companyEmail = "info_" + "@" + companyArray[0] + ".com";
	String fName = fake.name().firstName();
	String lName = fake.name().lastName();
	String contactEmail = fName.substring(0, 1) + "." + lName + "@example.com";
	int RandomType = fake.number().numberBetween(0, 3);
	String pec;
	if (companyArray.length > 1) {
		pec = companyArray[0] + "." + companyArray[1] + "@pec.it";		
	} else {
		pec = companyArray[0] + "@pec.it";
	}
	
	
	return Customer.builder()
			.businessName(companyName)
			.vatNumber(fake.number().numberBetween(10000000001l, 100000000000l))
			.contactName(fName + " " + lName)
			.email(companyEmail)
			.insertData(LocalDate.of(2023, 5, (int) Math.floor(Math.random() * 30 + 1)))
			.lastContactData(LocalDate.of(2023, 5, (int) Math.floor(Math.random() * 30 + 1)))
			.pec(pec)
			.phoneNumber(3490000000l + fake.number().numberBetween(1l, 10000000l))
			.contactEmail(contactEmail)
			.contactPhone(3490000000l + fake.number().numberBetween(1l, 10000000l))
			.costumerType(getRandomCustomeType(RandomType))
			.operativeAdress(null)
			.legalAdress(null)
			.build();
    }
    
    private CostumerType getRandomCustomeType(int random) {
    	CostumerType type = null;
    	switch (random) {
    	case 0 -> type = CostumerType.PA;
    	case 1 -> type = CostumerType.SAS;
    	case 2 -> type = CostumerType.SPA;
    	case 3 -> type = CostumerType.SRL;
    	}
    	return type;
    }
}
