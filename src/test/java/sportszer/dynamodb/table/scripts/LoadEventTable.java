package sportszer.dynamodb.table.scripts;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;

import sportszer.api.events.bean.Event;
import sportszer.api.events.bean.EventLocation;
import sportszer.api.events.bean.EventRecurrence;

public class LoadEventTable {

	//private static AmazonDynamoDBClient client = new AmazonDynamoDBClient().withEndpoint("http://localhost:8000");
	private static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider()).withRegion(Regions.US_WEST_2);
	
	public static void main(String[] args) {

		DynamoDBMapper mapper = new DynamoDBMapper(client);

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		String[] recurrenceDaysArray = { "Monday", "Wednesday" };
		Set<String> recurrenceDays = new HashSet<String>(Arrays.asList(recurrenceDaysArray));

		Event event1 = new Event("Mighty Kids 1", "Taekwando for toddlers", "Summer", true,
				new EventLocation("Ekstam Dr", "Bloomington", "IL", "61705"), calendar.getTime(), calendar.getTime(),
				"1800", "1900", new EventRecurrence("Weekly", recurrenceDays));

		Event event2 = new Event("Soccer for Tots", "Soccer for toddlers", "Summer", true,
				new EventLocation("Ekstam Dr", "Bloomington", "IL", "61705"), calendar.getTime(), calendar.getTime(),
				"1800", "1900", new EventRecurrence("Weekly", recurrenceDays));

		List<FailedBatch> failedBatchs = mapper.batchSave(Arrays.asList(event1, event2));
		
		if(failedBatchs.isEmpty()){
			System.out.println("2 Event records loaded");	
		}else{
			System.out.println("Error occurred while loading Event records");
		}		
	}
}
