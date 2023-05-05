package com.epic_energies.business.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provinces")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String abbr;
	private String name;
	private String county;
	@OneToMany(mappedBy="province", fetch = FetchType.EAGER)
	private List<Municipality> municipalities;
	
}
