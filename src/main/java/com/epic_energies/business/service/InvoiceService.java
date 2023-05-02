package com.epic_energies.business.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.repository.InvoiceDao;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceDao fatturaDao;
    @Autowired
    @Qualifier("FakeInvoice")
    private ObjectProvider<Invoice> objFattura;

    public void insertInvoice(Invoice f) {
	fatturaDao.save(f);
    }

    public void createInvoice() {
	insertInvoice(objFattura.getObject());
    }

}
