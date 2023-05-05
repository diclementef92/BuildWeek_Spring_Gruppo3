package com.epic_energies.business.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epic_energies.business.model.Address;
import com.epic_energies.business.model.Customer;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

    Optional<List<Customer>> findAllByBusinessName(String name);

	@Query("SELECT c FROM Customer c WHERE LOWER(c.businessName) LIKE LOWER(CONCAT('%', :name, '%'))")
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

	@Query("SELECT c FROM Customer c INNER JOIN c.legalAddress la WHERE c.legalAddress =:legalAddress")
	Optional<List<Customer>> findAllByLegalAddress(@Param("legalAddress") Address legalAddress);

	@Query("SELECT c FROM Customer c INNER JOIN c.operativeAddress la WHERE c.operativeAddress =:operativeAddress")
	Optional<List<Customer>> findAllByOperativeAddress(@Param("operativeAddress") Address operativeAddress);

	// PAGEABLE QUERIES
    Page<Customer> findAll(Pageable pageable);

	@Query("SELECT c FROM Customer c WHERE LOWER(c.businessName) LIKE LOWER(CONCAT('%', :name, '%'))")
	Page<Customer> findAllByBusinessNameLike(@Param("name")String name, Pageable pageable);
	
	@Query("SELECT c FROM Customer c WHERE c.annualIncome BETWEEN :amount1 AND :amount2")
	Page<Customer> findCustomersByIncomeRange(
			@Param("amount1") BigDecimal amount1, 
			@Param("amount1") BigDecimal amount2, 
			Pageable pageable);
	
}
