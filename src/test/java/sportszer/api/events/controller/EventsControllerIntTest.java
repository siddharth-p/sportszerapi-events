package sportszer.api.events.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
import sportszer.api.events.bean.EventLocation;
import sportszer.api.events.bean.EventRecurrence;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EventsApplication.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Ignore
public class EventsControllerIntTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;
	
	private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/v1/events");
		template = new TestRestTemplate();
	}

	@Test	
	public void getHello() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		JSONAssert.assertEquals(getJSON(getEvents()), response.getBody(), true);
	}

	private Event[] getEvents() {
		Event[] events = new Event[2];
		events[0] = buildEvent("Mighty Kids 1", "Taekwando introduction to Kids");
		events[1] = buildEvent("Mighty Kids 2", "Taekwando for experienced Kids");

		return events;
	}

	private String getJSON(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	private Event buildEvent(String name, String description) {		
		String[] recurrenceDaysArray = { "Monday", "Wednesday" };
		Set<String> recurrenceDays = new HashSet<String>(Arrays.asList(recurrenceDaysArray));

		return new Event(name, description, "Summer", true,
				new EventLocation("Ekstam Dr", "Bloomington", "IL", "61705"), calendar.getTime(), calendar.getTime(),
				"1800", "1900", new EventRecurrence("Weekly", recurrenceDays),"1234");
	}
}
