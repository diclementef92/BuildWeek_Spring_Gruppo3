package com.epic_energies.test_unit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.epic_energies.business.model.Customer;

class CustomerTest {

	Customer c = new Customer();
		
	@Test
	void CustomerAnnualIncome() {
		c.setAnnualIncome(300);
		assertEquals(300, c.getAnnualIncome());
	}
	
	@Test
	void CustomerBusinessName() {
		c.setBusinessName("Amazon");
		assertEquals("Amazon", c.getBusinessName());
	}
	
	@Test
	void CustomerContactEmail() {
		c.setContactEmail("amazon@gmail.com");
		assertEquals("amazon@gmail.com", c.getContactEmail());
	}
	
		@Test
	void testGetId() {
		c.setId(4l);
		assertEquals(4l, c.getId());
	}

	@Test
	void testGetVatNumber() {
		c.setVatNumber(235l);
		assertEquals(235l, c.getVatNumber());
	}

	@Test
	void testGetPhoneNumber() {
		c.setPhoneNumber(12312334l);
		assertEquals(12312334l, c.getPhoneNumber());
	}

	@Test
	void testGetContactName() {
		c.setContactName("piero angela");
		assertEquals("piero angela", c.getContactName());
	}
	
	@Test
	void testGetContactPhone() {
		c.setContactPhone(233244556l);
		assertEquals(233244556l, c.getContactPhone());
	}
}
