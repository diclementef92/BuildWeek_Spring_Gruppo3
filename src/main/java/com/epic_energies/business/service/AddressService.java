package com.epic_energies.business.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Address;
import com.epic_energies.business.model.Municipality;
import com.epic_energies.business.repository.AddressDAO;
import com.github.javafaker.Faker;

@Service
public class AddressService {

    @Autowired
    private AddressDAO addressDao;
    @Autowired
    private MunicipalityService municipalityService;

    public void createFakeAddress(Address a) {
	Faker fake = Faker.instance(new Locale("en-EN"));
	List<Municipality> list_municipality = municipalityService.findAllMunicipality();
	Integer random = fake.number().numberBetween(0, list_municipality.size() - 1);
	Municipality m = list_municipality.get(random);
	a.setMunicipality(m);
	addressDao.save(a);
    }
}
