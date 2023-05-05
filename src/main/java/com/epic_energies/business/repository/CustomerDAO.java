package com.epic_energies.business.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Query("SELECT c FROM Customer c WHERE c.contactName LIKE LOWER(CONCAT('%', :name, '%'))")
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

//	@Query(value = "select c.* " + "from customers c " + "inner join addresses a on a.id = c.legal_address_id "
//			+ "inner join municipalities m ON m.id = a.municipality_id "
//			+ "inner join provinces p ON p.id = m.province_id "
//			+ "where p.abbr=:province", nativeQuery = true)
//	Optional<List<Customer>> findAllByLegalAddressProvince(@Param("province") String province);

	@Query("select c FROM Customer c " + "INNER JOIN c.legalAddress la " + "INNER JOIN la.Municipality m "
			+ "INNER JOIN m.province p " + "WHERE LOWER(p.name) LIKE CONCAT('%', :province, '%')")
	Optional<List<Customer>> findAllByLegalAddressProvince(@Param("province") String province);

	@Query("select c FROM Customer c " + "INNER JOIN c.operativeAddress la " + "INNER JOIN la.Municipality m "
			+ "INNER JOIN m.province p " + "WHERE LOWER(p.name) LIKE CONCAT('%', :province, '%')")
	Optional<List<Customer>> findAllByOperativeAddressProvince(@Param("province") String province);

	// PAGEABLE QUERIES
    Page<Customer> findAll(Pageable pageable);

	@Query("SELECT c FROM Customer c WHERE LOWER(c.businessName) LIKE LOWER(CONCAT('%', :name, '%'))")
	Page<Customer> findAllByBusinessNameLike(@Param("name")String name, Pageable pageable);
	
	@Query("SELECT c FROM Customer c WHERE c.annualIncome BETWEEN :amount1 AND :amount2")
	Page<Customer> findCustomersByIncomeRange(
			@Param("amount1") BigDecimal amount1, 
			@Param("amount2") BigDecimal amount2, 
			Pageable pageable);
	
	@Query("SELECT c FROM Customer c WHERE c.insertData BETWEEN :data1 AND :data2")
	Page<Customer> findCustomerByInsertData(
			@Param("data1") LocalDate amount1, 
			@Param("data2") LocalDate amount2, 
			Pageable pageable);
	
	@Query("SELECT c FROM Customer c WHERE c.lastContactData BETWEEN :data1 AND :data2")
	Page<Customer> findCustomerByLastContactData(
			@Param("data1") LocalDate amount1, 
			@Param("data2") LocalDate amount2, 
			Pageable pageable);
}
