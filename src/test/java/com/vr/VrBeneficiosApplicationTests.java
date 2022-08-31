package com.vr;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(classes = VrBeneficiosApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
class VrBeneficiosApplicationTests {

	
	void contextLoads() {
		VrBeneficiosApplication.main(new String[] {});
	}

}
