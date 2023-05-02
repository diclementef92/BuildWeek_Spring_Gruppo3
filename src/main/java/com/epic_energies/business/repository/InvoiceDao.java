package com.epic_energies.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epic_energies.business.model.Invoice;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Long> {

}
