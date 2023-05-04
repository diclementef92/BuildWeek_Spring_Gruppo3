package com.epic_energies.test_unit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.epic_energies.business.model.Municipality;

class MunicipalityTest {

	Municipality c = new Municipality();
	
	@Test
	void MunicipalityId() {
		c.setId(23l);
		assertEquals(23l, c.getId());
	}

	@Test
	void MunicipalityGetName() {
		c.setName("Palermo");
		assertEquals("Palermo", c.getName());
	}

}
