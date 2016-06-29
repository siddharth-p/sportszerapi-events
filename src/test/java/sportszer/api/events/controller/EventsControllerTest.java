package sportszer.api.events.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sportszer.api.events.EventsApplication;
import sportszer.api.events.bean.Event;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(EventsApplication.class)
@WebAppConfiguration
public class EventsControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void retrieveEvents() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/events")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(getEvents())));
	}
	
	private String getEvents() throws JsonProcessingException{
		Event[] events = new Event[2];
		events[0] = buildEvent("Mighty Kids 1", "Taekwando introduction to Kids");
		events[1] = buildEvent("Mighty Kids 2", "Taekwando for experienced Kids");

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(events);
	}
	
	private Event buildEvent(String name, String description) {
		return new Event(name, description);
	}
}
