package com.epic_energies.business.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operative_adresses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Address {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private Integer streetNumber;
    private String place;
    private Integer postCode;	
    private E_AddressType addressType;

//  @ManyToOne
//  private String Municipality;

}
