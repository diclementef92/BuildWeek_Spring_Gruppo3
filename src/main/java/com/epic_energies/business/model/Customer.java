package com.epic_energies.business.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Integer annualIncome;
    private String pec;
    private Long phoneNumber;
    private String contactEmail;
    private String contactName;
    private Long contactPhone;
    
    @Enumerated(EnumType.STRING)
    private E_CustomerType costumerType;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Address legalAddress;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Address operativeAddress;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    @JsonIgnoreProperties({ "year", "number", "customer" })
    private List<Invoice> list_invoices;

}
