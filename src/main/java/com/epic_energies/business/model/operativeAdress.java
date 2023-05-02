package com.epic_energies.business.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Operative_lAdresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class operativeAdress {
	private String streetName;
	private Integer streetNumber;
	private String place;
	private String postCode;

}
