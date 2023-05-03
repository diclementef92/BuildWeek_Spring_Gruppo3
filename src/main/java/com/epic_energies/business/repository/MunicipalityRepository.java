package com.epic_energies.business.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epic_energies.business.model.Municipality;
import com.epic_energies.business.model.Province;

public interface MunicipalityRepository extends CrudRepository<Municipality, Long> {
	
	List<Municipality> findByName(String name);
	List<Municipality> findByProvince(Province province);
	
}
