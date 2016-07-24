package sportszer.api.events.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import sportszer.api.events.bean.Event;

/**
 * Sportszer Events Data Access
 * 
 * @author Siddharth Pandey
 * @since May 20, 2016
 */
@Repository
public class EventsDAO {

	/**
	 * TODO Inject env specific AmazonDynamoDBClient bean
	 * 
	 * Workstation client - AmazonDynamoDBClient client = new
	 * AmazonDynamoDBClient().withEndpoint("http://localhost:8000");
	 */
	AmazonDynamoDBClient client = new AmazonDynamoDBClient().withRegion(Regions.US_WEST_2);

	/**
	 * @return all Sportszer Events
	 */
	public Event[] retrieveEvents() {

		DynamoDBMapper dbMapper = new DynamoDBMapper(client);

		List<Event> eventList = dbMapper.scan(Event.class, new DynamoDBScanExpression());
		Event[] events = new Event[eventList.size()];

		return eventList.toArray(events);
	}

	/**
	 * Add a Sportszer Event
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		DynamoDBMapper dbMapper = new DynamoDBMapper(client);
		dbMapper.save(event);
	}

	/**
	 * Update a Sportszer Event
	 * 
	 * @param event
	 */
	public void updateEvent(Event event) {
		DynamoDBMapper dbMapper = new DynamoDBMapper(client);
		dbMapper.save(event, new DynamoDBMapperConfig(SaveBehavior.CLOBBER));
	}

	/**
	 * Delete a Sportszer Event
	 * 
	 * @param event
	 */
	public void deleteEvent(Event event) {
		DynamoDBMapper dbMapper = new DynamoDBMapper(client);
		dbMapper.delete(event);
	}
}
