package com.epic_energies.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epic_energies.business.model.Fattura;

public interface FatturaDao extends JpaRepository<Fattura, Long> {

}
