package com.epic_energies.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.epic_energies.business.model.Address;
import com.epic_energies.business.model.E_AddressType;

@Repository
public interface AddressDAO extends CrudRepository<Address, Long>, PagingAndSortingRepository<Address, Long> {
	// PAGEABLE QUERIES
	Page<Address> findAll(Pageable pageable);
	Page<Address> findByAddressType(E_AddressType addressType, Pageable pageable);
}
