package com.epic_energies.business.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.epic_energies.business.service.MunicipalityService;
import com.epic_energies.business.service.ProvinceService;

@Component
public class BusinessRunner implements ApplicationRunner {

	@Autowired ProvinceService proService;
	@Autowired MunicipalityService munService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		// IF TABLE PROVINCES ON DB HAS LESS THAN 1 ROW IN IT, METHOD WILL READ THE .CLS FILE AND POPULATE THE DB
		if(proService.findAllProvinces().size() < 1) {
			proService.importProvinces();
		}
		
		// IF TABLE MUNICIPALITIES ON DB HAS LESS THAN 1 ROW IN IT, METHOD WILL READ THE .CLS FILE AND POPULATE THE DB
		if (munService.findAllMunicipality().size() < 1) {
			munService.importAllMunicipalities();
		}
		
	}

}
