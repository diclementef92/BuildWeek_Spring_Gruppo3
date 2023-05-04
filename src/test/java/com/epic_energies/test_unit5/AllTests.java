package com.epic_energies.test_unit5;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AddressControllerTest.class, AddressServiceTest.class, AddressTest.class, AuthControllerTest.class,
		AuthRunnerTest.class, AuthServiceImplTest.class, BusinessConfigTest.class, CustomerControllerTest.class,
		CustomerServiceTest.class, CustomerTest.class, CustomExceptionHandlerTest.class,
		CustomUserDetailsServiceTest.class, ErrorDetails.class, InvoiceControllerTest.class, InvoiceServiceTest.class,
		InvoiceTest.class, JwtAuthenticationEntryPointTest.class, JwtAuthenticationFilterTest.class,
		JWTAuthResponseTest.class, JwtTokenProviderTest.class, LoginDtoTest.class, MunicipalityServiceTest.class,
		MunicipalityTest.class, ProvinceServiceTest.class, ProvinceTest.class, RegisterDtoTest.class, RoleTest.class,
		SecurityConfigTest.class, SpringSecurityApplicationTest.class, TestControllerTest.class })

public class AllTests {

}
