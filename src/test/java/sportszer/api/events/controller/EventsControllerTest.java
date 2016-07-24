package sportszer.api.events.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.easymock.EasyMock;
import org.junit.After;
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
import sportszer.api.events.bean.EventLocation;
import sportszer.api.events.bean.EventRecurrence;
import sportszer.api.events.dao.EventsDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=EventsApplication.class, locations={"/mock-applicationContext.xml"})
@WebAppConfiguration
public class EventsControllerTest {

	private MockMvc mvc;	

	@Autowired
	private EventsDAO mockEventsDAO;

	@Autowired
	private WebApplicationContext context;
	
	private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		EasyMock.reset(mockEventsDAO);
	}
	
	@After
	public void tearDOwn(){
		EasyMock.verify(mockEventsDAO);
	}

	@Test
	public void testRetrieveEvents() throws Exception {
		setRetrieveEventsMockExpectations();

		mvc.perform(MockMvcRequestBuilders.get("/v1/events"))				
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(getJSON(getEvents())));	
	}
	
	@Test
	public void testAddEvent() throws Exception {
		Event event = buildEvent("Mighty Kids 1", "Taekwando introduction to Kids");
		setAddEventMockExpectations(event);		

		mvc.perform(MockMvcRequestBuilders.post("/v1/events")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJSON(event)))				
				.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateEvent() throws Exception {
		Event event = buildEvent("Mighty Kids 1", "Taekwando introduction to Kids");
		setUpdateEventMockExpectations(event);		

		mvc.perform(MockMvcRequestBuilders.put("/v1/events")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJSON(event)))				
				.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteEvent() throws Exception {
		Event event = buildEvent("Mighty Kids 1", "Taekwando introduction to Kids");
		setDeleteEventMockExpectations(event);		

		mvc.perform(MockMvcRequestBuilders.delete("/v1/events")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJSON(event)))				
				.andExpect(status().isOk());
	}
	
	@Test
	public void testThrowException() throws Exception {
		Event event = buildEvent("Mighty Kids 1", "Taekwando introduction to Kids");
		setUpdateEventMockExpectationsToThrowException(event);		

		mvc.perform(MockMvcRequestBuilders.put("/v1/events")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJSON(event)))				
				.andExpect(status().isInternalServerError());
	}	
	
	private void setAddEventMockExpectations(Event event) {
		mockEventsDAO.addEvent(event);
		EasyMock.expectLastCall();
		EasyMock.replay(mockEventsDAO);
	}
	
	private void setUpdateEventMockExpectations(Event event) {
		mockEventsDAO.updateEvent(event);
		EasyMock.expectLastCall();
		EasyMock.replay(mockEventsDAO);
	}	
	
	private void setUpdateEventMockExpectationsToThrowException(Event event) {
		mockEventsDAO.updateEvent(event);
		EasyMock.expectLastCall().andThrow(new RuntimeException());
		EasyMock.replay(mockEventsDAO);
	}
	
	private void setDeleteEventMockExpectations(Event event) {
		mockEventsDAO.deleteEvent(event);
		EasyMock.expectLastCall();
		EasyMock.replay(mockEventsDAO);
	}

	private void setRetrieveEventsMockExpectations() {
		EasyMock.expect(mockEventsDAO.retrieveEvents()).andReturn(getEvents());
		EasyMock.replay(mockEventsDAO);
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
				"1800", "1900", new EventRecurrence("Weekly", recurrenceDays));
	}
}
