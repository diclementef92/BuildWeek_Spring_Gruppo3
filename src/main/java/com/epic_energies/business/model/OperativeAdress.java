package com.epic_energies.business.model;

import jakarta.persistence.Entity;

@Entity
public class OperativeAdress {
	private String streetName;
	private String streetNumber;
	private String place;
	private Integer via;
	private String postCode;

}
