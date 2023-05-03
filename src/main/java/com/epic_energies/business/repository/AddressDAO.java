package com.epic_energies.business.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.epic_energies.business.model.Address;

@Repository
public interface AddressDAO extends CrudRepository<Address, Long>, PagingAndSortingRepository<Address, Long> {

}