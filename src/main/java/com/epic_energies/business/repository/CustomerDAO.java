package com.epic_energies.business.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.epic_energies.business.model.Customer;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long>{

}
