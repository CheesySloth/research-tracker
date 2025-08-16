package com.rt.prototype;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import jakarta.activation.DataSource;

@SpringBootTest
@ActiveProfiles("test")
class PrototypeApplicationTests {

	@Test
	void contextLoads() {
	}

}
