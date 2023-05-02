package com.epic_energies.business.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epic_energies.business.model.Province;

@Repository
public interface ProvinceRepository extends CrudRepository<Province, Long> {
	
	List<Province> findByCounty(String county);
	List<Province> findByName(String name);
	
}
