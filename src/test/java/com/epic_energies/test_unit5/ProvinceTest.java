package com.epic_energies.test_unit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.epic_energies.business.model.Province;

class ProvinceTest {

	Province c = new Province();
	
	@Test
	void ProvinceId() {
		c.setId(23l);
		assertEquals(23l, c.getId());
	}
	
	@Test
	void ProvinceAbbr() {
		c.setAbbr("VR");
		assertEquals("VR", c.getAbbr());
	}
	
	@Test
	void ProvinceName() {
		c.setName("Verona");
		assertEquals("Verona", c.getName());
	}
	
	@Test
	void ProvinceCounty() {
		c.setCounty("Bergamo");
		assertEquals("Bergamo", c.getCounty());
	}

}
