package com.local.localdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LocaldemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGreeting() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/greeting"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello, Spring Boot 3.3! 🌟"));
	}
}
