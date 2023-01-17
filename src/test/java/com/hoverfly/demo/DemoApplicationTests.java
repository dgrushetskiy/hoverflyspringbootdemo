package com.hoverfly.demo;


import com.hoverfly.demo.service.UniService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@ActiveProfiles(value = "iTest")
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UniService uniService;

	@ParameterizedTest
	@CsvSource({"Monaco"})
	void getUniversitiesForCountry(String country) throws Exception {
		String actualResponse = mockMvc.perform(get("/api/university")
						.contentType("application/json")
						.param("country", country)
				)
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}

}
