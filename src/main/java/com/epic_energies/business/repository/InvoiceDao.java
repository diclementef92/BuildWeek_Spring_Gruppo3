package com.epic_energies.business.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT i FROM Invoice i WHERE i.amount BETWEEN ?1 AND ?2")
    List<Invoice> findByDateBetween(Date date1, Date date2);

    // filter per anno
    List<Invoice> findByYear(Integer year);

    // filter importo fino a...
    List<Invoice> findByAmountLessThan(BigDecimal amount);

    // filter importo da...
    List<Invoice> findByAmountGreaterThan(BigDecimal amount);

    // filter per range d'importi
    @Query("SELECT i FROM Invoice i WHERE i.amount BETWEEN :amount1 AND :amount2")
    List<Invoice> findByAmountBetween(@Param("amount1") BigDecimal amount1, @Param("amount2") BigDecimal amount2);

    // ordine crescente degli importi fattura
    @Query("SELECT i FROM Invoice i ORDER BY i.amount ASC")
    Optional<List<Invoice>> findByAmount();

    // ordine di data inserimento dalla più recente
    @Query("SELECT i FROM Invoice i ORDER BY i.date DESC")
    Optional<List<Invoice>> getAllInvoiceOrderByDate();

    // ordine di inserimento descrescente della fattura per anno
    @Query("SELECT i FROM Invoice i ORDER BY i.year DESC")
    Optional<List<Invoice>> findByYear();

    // PAGEABLE QUERIES
    Page<Invoice> findAll(Pageable pageable);

    Page<Invoice> findByCustomer(Customer customer, Pageable pageable);

    Page<Invoice> findByInvoiceStatus(InvoiceStatus invoiceStatus, Pageable pageable);

    Page<Invoice> findByDate(Date date, Pageable pageable);

    Page<Invoice> findByYear(Integer year, Pageable pageable);

    Page<Invoice> findByAmountBetween(BigDecimal amount1, BigDecimal amount2, Pageable pageable);
}
