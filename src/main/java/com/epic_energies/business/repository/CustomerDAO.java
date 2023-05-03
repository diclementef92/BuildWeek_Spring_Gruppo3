package com.epic_energies.business.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.epic_energies.business.model.Customer;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

	Optional<Customer> findById(Long id);

	Optional<List<Customer>> findAllByBusinessName(String name);

	@Query("SELECT c FROM Customer c WHERE c.businessName LIKE '%name%'")
	Optional<List<Customer>> findAllByBusinessNameLike(@Param("name") String name);

	@Query("SELECT c FROM Customer c WHERE c.contactName LIKE '%name%'")
	Optional<List<Customer>> findAllByContactNameLike(@Param("name") String name);

	// ordine alfabetico per business name
	@Query("SELECT c FROM Customer c ORDER BY c.businessName ASC")
	Optional<List<Customer>> getAllCustomersOrderByBusinessName();

	// ordine decrescente di fatturato
	@Query("SELECT c FROM Customer c ORDER BY c.annualIncome DESC")
	Optional<List<Customer>> getAllCustomersOrderByAnnualIncome();

	// ordine di data inserimento dalla più recente
	@Query("SELECT c FROM Customer c ORDER BY c.insertData DESC")
	Optional<List<Customer>> getAllCustomersOrderByInsertData();

	// ordine di data ultimo contatto dalla più recente
	@Query("SELECT c FROM Customer c ORDER BY c.lastContactData DESC")
	Optional<List<Customer>> getAllCustomersOrderByLastContactData();

}
