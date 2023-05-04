package com.epic_energies.business.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.repository.InvoiceDao;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceDao fatturaDao;

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

    // -------------- FIND AND GET --------------

    public Invoice FindInvoiceById(Long id) {
	if (fatturaDao.existsById(id)) {
	    return fatturaDao.findById(id).get();
	} else {
	    throw new EntityNotFoundException("Invoice with ID --> " + id + " doesn't exists on Database!");
	}

    }

    public Optional<List<Invoice>> getAllinvoiceInvoicesOrderByLastData() {
	return fatturaDao.findByDate();
    }

    public Optional<List<Invoice>> getAllinvoiceInvoicesOrderByLastYear() {
	return fatturaDao.findByYear();
    }

    public Optional<List<Invoice>> findInvoiceByAmount() {
	return fatturaDao.findByAmount();
    }

    public List<Invoice> findAll() {
	return (List<Invoice>) fatturaDao.findAll();
    }

}
