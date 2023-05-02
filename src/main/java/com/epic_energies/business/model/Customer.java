package com.epic_energies.business.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "costumers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private OperativeAdress operativeAdress;
    @OneToMany
    private List<Fattura> fattura;

}
