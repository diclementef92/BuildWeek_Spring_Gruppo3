package com.epic_energies.business.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {

    private String businessName;
    private String vatNumber;
    private String email;
    private Date insertData;
    private Date lastContactData;
    private Integer pec;
    private Integer phoneNumber;
    private String contactEmail;
    private String contactName;
    private String contactPhone;
    @Enumerated(EnumType.STRING)
    private CostumerType CostumerType;
    @OneToOne
    private LegalAdress legalAdress;
    @OneToOne
    private LegalAdress operativeAdress;
    @OneToMany
    private Fattura fattura;

}
