/* See the file "LICENSE" for the full license governing this code. */
package com.cognizant.covr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public final class CovrControllerTest {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void listReturnsEmptyList() throws Exception {
		// Setup


		// Exercise
		final String actual = mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		// Assert
		assertThat(actual, is("[]"));

		// Teardown
	}

	@Test
	public void returnJsonWhenGivenJson() throws Exception {
		// Setup

		String newRestaurant = "{id='1', text='TEXT'}";


		//Exercise
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/")
				.accept(MediaType.APPLICATION_JSON).content(newRestaurant)
				.contentType(MediaType.APPLICATION_JSON);

		final String content = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();


		// Assert
		assertThat(content, is("{id='1', text='TEXT'}"));
	}

}
