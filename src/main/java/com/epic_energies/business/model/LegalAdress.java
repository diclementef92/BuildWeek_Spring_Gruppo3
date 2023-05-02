package com.epic_energies.business.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Legal_Adresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LegalAdress {
	private String streetName;
	private Integer streetNumber;
	private String place;
	private String postCode;
	@ManyToOne
	private Municipality Municipality;
}
