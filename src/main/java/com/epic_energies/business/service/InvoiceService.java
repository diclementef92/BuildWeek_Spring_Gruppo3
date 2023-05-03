package com.epic_energies.business.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.repository.CustomerDAO;
import com.epic_energies.business.repository.InvoiceDao;
import com.github.javafaker.Faker;

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

    public void insertInvoice(Invoice f, Long id) {
	Customer c = customerService.findCustomerById(id);
	f.setCustomer(c);
	fatturaDao.save(f);
    }

    public void createInvoice(Long id) {
	insertInvoice(objFattura.getObject(), id);
    }

    public void createFakeInvoice() {
	Faker fake = Faker.instance(new Locale("en-EN"));
	List<Customer> list_customer = customerService.findAll();
	Integer random = fake.number().numberBetween(0, list_customer.size() - 1);
	Customer c = list_customer.get(random);
	Invoice i = objFattura.getObject();
	i.setCustomer(c);
	fatturaDao.save(i);
    }

}
