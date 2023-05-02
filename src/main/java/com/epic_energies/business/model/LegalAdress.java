package com.epic_energies.business.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "legal_adresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LegalAdress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String streetName;
    private Integer streetNumber;
    private String place;
    private Integer postCode;

    //@ManyToOne
    //private String Municipality;

}
