package sportszer.api.events.dao;

import org.springframework.stereotype.Repository;

import sportszer.api.events.bean.Event;

/**
 * Sportszer Events Data Access
 * 
 * @author Siddharth Pandey
 * @since May 20, 2016
 */
@Repository
public class EventsRetriever {

	public Event[] retrieveEvents() {

		/*
		 * AmazonDynamoDBClient client = new
		 * AmazonDynamoDBClient().withEndpoint("http://localhost:8000");
		 * DynamoDB dynamoDB = new DynamoDB(client); Table table =
		 * dynamoDB.getTable("Event");
		 * 
		 * String eventId = "123456789"; GetItemSpec spec = new
		 * GetItemSpec().withPrimaryKey("eventId", eventId);
		 * 
		 * try { System.out.println("Attempting to read the item..."); Item
		 * outcome = table.getItem(spec); System.out.println(
		 * "GetItem succeeded: " + outcome);
		 * 
		 * } catch (Exception e) { System.err.println("Unable to read item: " +
		 * eventId); System.err.println(e.getMessage()); }
		 */

		Event[] events = new Event[2];

		events[0] = buildEvent("Mighty Kids 1", "Taekwando introduction to Kids");
		events[1] = buildEvent("Mighty Kids 2", "Taekwando for experienced Kids");

		return events;
	}

	private Event buildEvent(String name, String description) {
		return new Event(name, description);
	}
}
