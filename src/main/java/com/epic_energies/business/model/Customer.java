package com.epic_energies.business.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessName;
    private Long vatNumber;
    private String email;
    private LocalDate insertData;
    private LocalDate lastContactData;
    private String pec;
    private Long phoneNumber;
    private String contactEmail;
    private String contactName;
    private Long contactPhone;

    @Enumerated(EnumType.STRING)
    private CostumerType costumerType;
    @OneToOne
    private LegalAdress legalAdress;
    @OneToOne
    private OperativeAdress operativeAdress;
    // @OneToMany
    // private List<Fattura> fatture;

}
