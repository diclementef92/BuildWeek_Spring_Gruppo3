package com.epic_energies.business.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epic_energies.business.model.Province;

@Repository
public interface ProvinceRepository extends CrudRepository<Province, Long> {
	
	Province findByName(String name);
	Province findByAbbr(String abbr);
	List<Province> findByCounty(String county);
	
}
