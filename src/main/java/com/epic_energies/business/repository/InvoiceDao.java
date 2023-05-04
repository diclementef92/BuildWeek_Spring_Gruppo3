package com.epic_energies.business.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.epic_energies.business.model.Customer;
import com.epic_energies.business.model.Invoice;
import com.epic_energies.business.model.InvoiceStatus;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Long>, PagingAndSortingRepository<Invoice, Long> {

    Optional<Invoice> findById(Long id);

    // Filter per cliente
    List<Invoice> findByCustomer(Customer customer);

    // filter per stato
    List<Invoice> findByInvoiceStatus(InvoiceStatus invoiceStatus);

    // filter per data precisa
    List<Invoice> findByDate(Date date);

    // filter date in range

    // filter per anno
    List<Invoice> findByYear(Integer year);

    // filter importo fino a...
    List<Invoice> findByAmountLessThan(BigDecimal amount);

    // filter importo da...
    List<Invoice> findByAmountGreaterThan(BigDecimal amount);

    // filter per range d'importi

    // ordine crescente degli importi fattura
    @Query("SELECT i FROM Invoice i ORDER BY i.amount ASC")
    Optional<List<Invoice>> findByAmount();

    // ordine di data inserimento dalla più recente
    @Query("SELECT i FROM Invoice i ORDER BY i.date DESC")
    Optional<List<Invoice>> findByDate();

    // ordine di inserimento descrescente della fattura per anno
    @Query("SELECT i FROM Invoice i ORDER BY i.year DESC")
    Optional<List<Invoice>> findByYear();

    // PAGEABLE QUERIES
    Page<Invoice> findAll(Pageable pageable);
}
