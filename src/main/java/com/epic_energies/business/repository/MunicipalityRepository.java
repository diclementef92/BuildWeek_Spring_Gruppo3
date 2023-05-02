package com.epic_energies.business.repository;

import org.springframework.data.repository.CrudRepository;

import com.epic_energies.business.model.Municipality;

public interface MunicipalityRepository extends CrudRepository<Municipality, Long> {
	
}
