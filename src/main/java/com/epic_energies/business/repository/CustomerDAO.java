package com.epic_energies.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.epic_energies.business.model.Customer;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

	Optional<Customer> findById(Long id);

	Optional<List<Customer>> findAllByBusinessName(String name);
}
