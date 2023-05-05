package com.epic_energies.test_unit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.epic_energies.business.model.Invoice;

class InvoiceTest {

	Invoice c = new Invoice();
	
	@Test
	void InvoiceId() {
		c.setId(23l);
		assertEquals(23l, c.getId());
	}
	
	@Test
	void InvoiceYear() {
		c.setYear(1985);
		assertEquals(1985, c.getYear());
	}
	
	@Test
	void testGetId() {
		c.setId(4l);
		assertEquals(4l, c.getId());
	}

}
