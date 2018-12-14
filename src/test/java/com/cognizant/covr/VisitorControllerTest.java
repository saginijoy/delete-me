/* See the file "LICENSE" for the full license governing this code. */
package com.cognizant.covr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public final class VisitorControllerTest {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private VisitorRepository visitorRepository;

	@Before
	public void beforeEach () {
		visitorRepository.deleteAll();
	}

	@After
	public void afterEach () {
		visitorRepository.deleteAll();
	}

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
	public void listReturnsSavedVisitors() throws Exception {
		// Setup
		final Visitor jason = new Visitor("12345678909", "Jason","+14155552345" );
		final Visitor samantha = new Visitor("855033291", "Samantha","+12866907311" );

		visitorRepository.save(jason);
		visitorRepository.save(samantha);

		// Exercise
		final String content = mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		final List<Visitor> actual = OBJECT_MAPPER.readValue(content, new ListOfVisitor());
		// Assert
		assertThat(actual, contains(jason, samantha));

		// Teardown
	}



	@Test
	public void createReturnsSavedVisitor() throws Exception {
		// Setup
		final Visitor expected = new Visitor("12345678909", "Jason","+14155552345" );
		final String visitorContent = OBJECT_MAPPER.writeValueAsString(expected);

		final String content = mockMvc.perform(post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(visitorContent))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		final Visitor actual = OBJECT_MAPPER.readValue(content, Visitor.class);

		// Assert
		assertThat(visitorRepository.count(), is(1L));
		assertThat(actual, allOf(
				hasProperty("id", is(any(Long.class))),
				hasProperty("employeeId", is(expected.getEmployeeId())),
				hasProperty("name", is(expected.getName())),
				hasProperty("mobile", is(expected.getMobile()))
		));
	}

	private static class ListOfVisitor extends TypeReference<List<Visitor>> {
	}
}
