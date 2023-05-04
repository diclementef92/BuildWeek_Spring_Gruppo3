package com.epic_energies.test_unit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.epic_energies.business.model.Customer;

class CustomerTest {

	Customer c = new Customer();
	
	@BeforeAll
	static void createCustomer() {
	}
	
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
	}

	@Test
	void testGetContactEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testGetContactName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetContactPhone() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCostumerType() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLegalAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOperativeAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testGetList_invoices() {
		fail("Not yet implemented");
	}

	@Test
	void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	void testSetBusinessName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetVatNumber() {
		fail("Not yet implemented");
	}

	@Test
	void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testSetInsertData() {
		fail("Not yet implemented");
	}

	@Test
	void testSetLastContactData() {
		fail("Not yet implemented");
	}

	@Test
	void testSetAnnualIncome() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPec() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPhoneNumber() {
		fail("Not yet implemented");
	}

	@Test
	void testSetContactEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testSetContactName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetContactPhone() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCostumerType() {
		fail("Not yet implemented");
	}

	@Test
	void testSetLegalAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testSetOperativeAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testSetList_invoices() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	void testCanEqual() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
