package com.epic_energies.business.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.epic_energies.business.service.FatturaService;

@Component
public class BusinessRunner implements ApplicationRunner {
    @Autowired
    private FatturaService fatturaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
	System.out.println("Fattura creata!");
	fatturaService.createFattura();

    }

}
