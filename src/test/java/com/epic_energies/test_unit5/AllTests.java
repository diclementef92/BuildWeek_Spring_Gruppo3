package com.epic_energies.test_unit5;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AddressTest.class, CustomerTest.class, InvoiceTest.class, MunicipalityTest.class, ProvinceTest.class })

public class AllTests {

}
