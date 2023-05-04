package com.epic_energies.business.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.repository.InvoiceDao;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    @Qualifier("FakeInvoice")
    private ObjectProvider<Invoice> objFattura;

    private Faker fake = Faker.instance(new Locale("en-EN"));

    public void persistInvoice(Invoice i, Long id) {
	Customer c = customerService.findCustomerById(id);
	i.setCustomer(c);

	invoiceDao.save(i);
    }

    public void createInvoice(Long id) {
	persistInvoice(objFattura.getObject(), id);
    }

    public void createFakeInvoice() {

	List<Customer> list_customer = customerService.findAll();
	Integer random = fake.number().numberBetween(0, list_customer.size() - 1);
	Customer c = list_customer.get(random);
	Invoice i = objFattura.getObject();

	i.setCustomer(c);
	invoiceDao.save(i);
    }

    public String updateInvoice(Invoice i) {
	if (invoiceDao.existsById(i.getId())) {
	    invoiceDao.save(i);
	    return "Invoice correctly updated on Database";
	} else {
	    throw new EntityNotFoundException("Invoice with ID --> " + i.getId() + " doesn't exists on Database!");
	}
    }

    public String deleteInvoice(Invoice i) {
	if (invoiceDao.existsById(i.getId())) {
	    invoiceDao.delete(i);
	    return "Invoice correctly deleted from Database";
	} else {
	    throw new EntityNotFoundException("Invoice with ID --> " + i.getId() + " doesn't exists on Database!");
	}
    }

    public String deleteInvoice(Long id) {
	if (invoiceDao.existsById(id)) {
	    invoiceDao.deleteById(id);
	    return "Invoice correctly deleted from Database";
	} else {
	    throw new EntityNotFoundException("Invoice with ID --> " + id + " doesn't exists on Database!");
	}
    }

    public Invoice FindInvoiceById(Long id) {
	if (invoiceDao.existsById(id)) {
	    return invoiceDao.findById(id).get();
	} else {
	    throw new EntityNotFoundException("Invoice with ID --> " + id + " doesn't exists on Database!");
	}
    }

    public Optional<List<Invoice>> getAllInvoicesOrderByLastData() {
	return invoiceDao.findByDate();
    }

    public List<Invoice> findAll() {
	return (List<Invoice>) invoiceDao.findAll();
    }

    public Page<Invoice> findAll(Pageable pageable) {
	return (Page<Invoice>) invoiceDao.findAll(pageable);
    }
}
