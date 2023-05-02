package com.epic_energies.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epic_energies.business.model.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

}
