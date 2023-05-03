package com.epic_energies.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epic_energies.business.model.Invoice;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findById(Long id);

    // ordine crescente degli importi fattura
    @Query("SELECT i FROM Invoice i ORDER BY i.amount ASC")
    Optional<List<Invoice>> findByAmount();

    // ordine di data inserimento dalla più recente
    @Query("SELECT i FROM Invoice i ORDER BY i.date DESC")
    Optional<List<Invoice>> findByDate();

    // ordine di inserimento descrescente della fattura per anno
    @Query("SELECT i FROM Invoice i ORDER BY i.year DESC")
    Optional<List<Invoice>> findByYear();

}
