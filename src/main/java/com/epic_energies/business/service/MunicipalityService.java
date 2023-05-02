package com.epic_energies.business.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic_energies.business.model.Municipality;
import com.epic_energies.business.model.Province;
import com.epic_energies.business.repository.MunicipalityRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MunicipalityService {

	@Autowired MunicipalityRepository muniRepo;
	@Autowired ProvinceService provService;
	
	// internal methods
	
	public void importAllMunicipalities() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src\\main\\resources\\data\\comuni-italiani.csv"));
		sc.useDelimiter(",");
		
		while (sc.hasNext()) {
	
			String[] fileToString = sc.next().split("\n");
			
			List<Province> allProvince =  provService.findAllProvinces();
			
			for (int i = 1; i < fileToString.length; i++) {
				String[] mToString = fileToString[i].split(";");
				
				List<Province> relatedProvince = allProvince.stream()
						.filter(p -> p.getName().toLowerCase().replaceAll("\\s", "").equalsIgnoreCase(mToString[3].toLowerCase().replaceAll("\\s", "")))
						.collect(Collectors.toList());
				if (relatedProvince.size() < 1) {
					log.warn(mToString[3]);
				}
				persistMunicipality(Municipality.builder().name(mToString[2]).province(relatedProvince.get(0)).build());				
			}
		}
		sc.close();
	}	
	
	// jpa methods
	public void persistMunicipality(Municipality m) {
		muniRepo.save(m);
		log.info("municipality correctly persited on Database");
	}
	
	public List<Municipality> findAllMunicipality() {
		return (List<Municipality>) muniRepo.findAll();
	}
	
	
	
}
