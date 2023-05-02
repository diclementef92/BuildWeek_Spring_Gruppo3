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
@Table(name = "municipalities")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Municipality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer historical_code;
	private Integer progressive_code;
	private String name;
	private String province;
	
	public static void importMunicipalities() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src\\main\\resources\\data\\comuni-italiani.csv"));
		sc.useDelimiter(",");
		
		while (sc.hasNext()) {
	
			String[] fileToString = sc.next().split("\n");
			
			for (int i = 1; i < fileToString.length; i++) {
				String[] mToString = fileToString[i].split(";");
				Municipality m = Municipality.builder()
						.historical_code(Integer.parseInt(mToString[0]))
						.progressive_code(Integer.parseInt(mToString[1]))
						.name(mToString[2]).province(mToString[3]).build();
				
				System.out.println(m);
				//QUI METODO SAVE DEL SERVICE PER SALVARE SU DB
				
			}
		}
		
		sc.close();
	}
}
