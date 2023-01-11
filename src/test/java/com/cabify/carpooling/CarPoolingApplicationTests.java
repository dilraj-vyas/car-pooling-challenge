package com.cabify.carpooling;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cabify.carpooling.model.Car;
import com.cabify.carpooling.model.Journey;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CarPoolingApplicationTests {
	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void itShouldHaveOkStatus(@Autowired MockMvc mvc) throws Exception {
		mvc.perform(get("/status")).andExpect(status().isOk());
	}

	@Test
	public void itShouldManageJourney(@Autowired MockMvc mvc) throws Exception {
		var cars = List.of(
			new Car(1, 4),
			new Car(2, 6));
		var journey = new Journey(1, 4);

		mvc.perform(put("/cars").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cars)))
			.andExpect(status().isOk());

		mvc.perform(post("/journey").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(journey)))
			.andExpect(status().isAccepted());

		mvc.perform(post("/locate").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content("ID="+journey.getId()))
			.andExpect(status().isOk())
			.andExpect(content().json("{\"id\":1,\"seats\":4}"));

		mvc.perform(post("/dropoff").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content("ID="+journey.getId()))
			.andExpect(status().isNoContent());
	}
}
