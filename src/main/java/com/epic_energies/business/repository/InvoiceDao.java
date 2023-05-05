package com.epic_energies.business.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	List<Invoice> findByDate(LocalDate date);

    // filter date in range
	@Query("SELECT i FROM Invoice i WHERE i.date BETWEEN ?1 AND ?2")
	List<Invoice> findByDateBetween(LocalDate date1, LocalDate date2);

    // filter per anno
    List<Invoice> findAllOrderedByYear(Integer year);

    // filter importo fino a...
    List<Invoice> findByAmountLessThan(BigDecimal amount);

    // filter importo da...
    List<Invoice> findByAmountGreaterThan(BigDecimal amount);

    // filter per range d'importi
    @Query("SELECT i FROM Invoice i WHERE i.amount BETWEEN :amount1 AND :amount2")
    List<Invoice> findByAmountBetween(@Param("amount1") BigDecimal amount1, @Param("amount2") BigDecimal amount2);

    // ordine crescente degli importi fattura
    @Query("SELECT i FROM Invoice i ORDER BY i.amount ASC")
	List<Invoice> findAllOrderedByAmount();

    // ordine di data inserimento dalla più recente
    @Query("SELECT i FROM Invoice i ORDER BY i.date DESC")
    List<Invoice> findAllOrderedByDate();

    // ordine di inserimento descrescente della fattura per anno
    @Query("SELECT i FROM Invoice i ORDER BY i.year DESC")
    List<Invoice> findAllOrderedByYear();

    // PAGEABLE QUERIES
    Page<Invoice> findAll(Pageable pageable);

    Page<Invoice> findByCustomer(Customer customer, Pageable pageable);

    Page<Invoice> findByInvoiceStatus(InvoiceStatus invoiceStatus, Pageable pageable);

	Page<Invoice> findByDate(LocalDate date, Pageable pageable);

    Page<Invoice> findByYear(Integer year, Pageable pageable);

    Page<Invoice> findByAmountBetween(BigDecimal amount1, BigDecimal amount2, Pageable pageable);
}
