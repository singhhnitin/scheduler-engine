package com.scheduler.api;

import com.scheduler.ApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApiApplication.class)
@EnabledIfEnvironmentVariable(named = "MONGODB_URI", matches = ".+")
class ApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
