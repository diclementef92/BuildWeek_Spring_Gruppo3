package com.epic_energies.test_unit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.epic_energies.business.model.Address;
import com.epic_energies.business.model.E_AddressType;

class AddressTest {
	
	Address c = new Address();
	
	@Test
	void testStreetName() {
		c.setStreetName("Via Prati");
		assertEquals("Via Prati", c.getStreetName());
	}
	
	@Test
	void testStreetNumber() {
		c.setStreetNumber(23);
		assertEquals(23, c.getStreetNumber());
	}
	
	@Test
	void testPlace() {
		c.setPlace("Genova");
		assertEquals("Genova", c.getPlace());
	}
	
	@Test
	void testGetId() {
		c.setId(4l);
		assertEquals(4l, c.getId());
	}

	@Test
	void testpostCode() {
		c.setPostCode(23553453);
		assertEquals(23553453, c.getPostCode());
	}

	@Test
	void testGetAddressType() {
		c.setAddressType(E_AddressType.LEGAL_ADDRESS);
		assertEquals(E_AddressType.LEGAL_ADDRESS, c.getAddressType());
	}

}
