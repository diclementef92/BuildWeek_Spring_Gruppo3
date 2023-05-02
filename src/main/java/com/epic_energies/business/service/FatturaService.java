package com.epic_energies.business.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Fattura;
import com.epic_energies.business.repository.FatturaDao;

@Service
public class FatturaService {
    @Autowired
    private FatturaDao fatturaDao;
    @Autowired
    @Qualifier("FakeFattura")
    private ObjectProvider<Fattura> objFattura;

    public void insertFattura(Fattura f) {
	fatturaDao.save(f);
    }

    public void createFattura() {
	insertFattura(objFattura.getObject());
    }

}
