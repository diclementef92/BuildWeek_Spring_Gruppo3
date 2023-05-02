package com.epic_energies.business.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	Long id;
	String abbr;
	String name;
	String county;
	
	public static void importProvinces() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src\\main\\resources\\data\\Province.csv"));
		sc.useDelimiter(",");
		
		while (sc.hasNext()) {

			String[] fileToString = sc.next().split("\n");
			for (String s : fileToString) {
				String[] pToString = s.split(";");
				Province p = Province.builder().abbr(pToString[0]).name(pToString[1]).county(pToString[2]).build();
				// QUI METODO SAVE PER SALVARE SU DB
			}
		}
		
		sc.close();
	}
}
