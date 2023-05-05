package com.epic_energies.business.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;
	private LocalDate date;
    private BigDecimal amount;
    private Integer number;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"email", "insertData", "lastContactData", "annualIncome", "pec", "legalAddress", "operativeAddress", "list_invoices"})
    private Customer customer;

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", year=" + year + ", date=" + date + ", amount=" + amount + ", number=" + number
				+ ", invoice_status=" + invoiceStatus + ", customer ID=" + customer.getId() + "]";
	}

    
}
