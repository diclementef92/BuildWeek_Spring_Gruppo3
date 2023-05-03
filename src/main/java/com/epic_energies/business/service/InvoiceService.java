package com.epic_energies.business.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.repository.CustomerDAO;
import com.epic_energies.business.repository.InvoiceDao;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceDao fatturaDao;

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private CustomerService customerService;

    @Autowired
    @Qualifier("FakeInvoice")
    private ObjectProvider<Invoice> objFattura;

    public void insertInvoice(Invoice f) {
	fatturaDao.save(f);
	f.setCustomer(customerDAO.findById(1l));

    }

    public void createInvoice() {
	insertInvoice(objFattura.getObject());
    }

}
