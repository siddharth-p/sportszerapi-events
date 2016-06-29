package sportszer.api.events.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sportszer.api.events.EventsApplication;
import sportszer.api.events.bean.Event;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EventsApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class EventsControllerIntTest {

    @Value("${local.server.port}")
    private int port;

	private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/v1/events");
		template = new TestRestTemplate();
	}

	@Test
	public void getHello() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), equalTo(getEvents()));
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
