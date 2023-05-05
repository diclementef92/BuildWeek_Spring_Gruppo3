package com.epic_energies.business.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.model.InvoiceStatus;
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

    public String persistInvoice(Invoice i, Long id) {
		Customer c = customerService.findCustomerById(id);
		i.setCustomer(c);
		invoiceDao.save(i);
	    return "Invoice correctly persisted on Database!";
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
    
    public List<Invoice> findAll() {
		return invoiceDao.findAll();
    }

	public List<Invoice> getAllInvoicesOrderByDate() {
		return invoiceDao.findAllOrderedByDate();
    }
   
	public List<Invoice> findAllOrderedByYear() {
		return invoiceDao.findAllOrderedByYear();
    }
	
	public List<Invoice> findAllOrderedByAmount() {
		return invoiceDao.findAllOrderedByAmount();
    }
	
	public List<Invoice> findByCustomer(Customer c) {
		return invoiceDao.findByCustomer(c);
	}
	
	public List<Invoice> getByCustomerId(Long id) {
		if (invoiceDao.existsById(id)) {
			Customer c = customerService.findCustomerById(id);
			return findByCustomer(c);
		} else {
			throw new EntityNotFoundException("Customer with ID --> " + id + " doesn't exists on Database!");
		}
	}
	
	public List<Invoice> findByStatus(InvoiceStatus status) {
		return invoiceDao.findByInvoiceStatus(status);
	}

	public List<Invoice> findByDateBetween(LocalDate d1, LocalDate d2) {
		return invoiceDao.findByDateBetween(d1, d2);
    }

	// PAGEABLE
    public Page<Invoice> findAll(Pageable pageable) {
		return invoiceDao.findAll(pageable);
    }

    public Page<Invoice> findAllByCustomer(Customer c, Pageable pageable) {
		return invoiceDao.findByCustomer(c, pageable);
    }

    public Page<Invoice> findAllByStatus(InvoiceStatus status, Pageable pageable) {
		return invoiceDao.findByInvoiceStatus(status, pageable);
    }

	public Page<Invoice> findAllByDate(LocalDate date, Pageable pageable) {
		return invoiceDao.findByDate(date, pageable);
    }

    public Page<Invoice> findAllByYear(Integer year, Pageable pageable) {
		return invoiceDao.findByYear(year, pageable);
    }

    public Page<Invoice> findAllByAmountRange(BigDecimal amount1, BigDecimal amount2, Pageable pageable) {
		return invoiceDao.findByAmountBetween(amount1, amount2, pageable);
    }
}
