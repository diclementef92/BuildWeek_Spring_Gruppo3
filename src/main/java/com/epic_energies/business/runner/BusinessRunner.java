package com.epic_energies.business.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.epic_energies.business.service.CustomerService;
import com.epic_energies.business.service.InvoiceService;
import com.epic_energies.business.service.MunicipalityService;
import com.epic_energies.business.service.ProvinceService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BusinessRunner implements ApplicationRunner {

    @Autowired
    private ProvinceService proService;
    @Autowired
    private MunicipalityService munService;
    @Autowired
    private CustomerService custService;
    @Autowired
    private InvoiceService invoiceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    // IF TABLE PROVINCES ON DB HAS LESS THAN 1 ROW IN IT, METHOD WILL READ THE .CLS
   	// FILE AND POPULATE THE DB
   	if (proService.findAllProvinces().size() < 1) {
   	    proService.importProvinces();
   	}

   	// IF TABLE MUNICIPALITIES ON DB HAS LESS THAN 1 ROW IN IT, METHOD WILL READ THE
   	// .CLS FILE AND POPULATE THE DB
   	if (munService.findAllMunicipality().size() < 1) {
   	    munService.importAllMunicipalities();
  	}
    	
	if (custService.findAll().isEmpty()) {
		for (int i = 0; i < 50; i++) {
			custService.persistFakeCustomer();
		}
		log.info("customers created!");
	}

	if (invoiceService.findAll().isEmpty()) {
		for (int i = 0; i < 150; i++) {
			invoiceService.createFakeInvoice();
		}
		log.info("invoices created!");

	}

    }

}
