package sportszer.dynamodb.table.scripts;

import java.util.Arrays;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class CreateEventTable {

	public static void main(String[] args) {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient().withEndpoint("http://localhost:8000");
		DynamoDB dynamoDB = new DynamoDB(client);

		String tableName = "Event";

		try {
			System.out.println("Attempting to create table; please wait...");

			Table table = dynamoDB.createTable(tableName, Arrays.asList(new KeySchemaElement("eventId", KeyType.HASH)), // Partitionkey
					Arrays.asList(new AttributeDefinition("eventId", ScalarAttributeType.S)),
					new ProvisionedThroughput(10L, 10L));
			
			table.waitForActive();
			System.out.println("Success. Table status: " + table.getDescription().getTableStatus());

		} catch (Exception e) {
			System.err.println("Unable to create table: ");
			System.err.println(e.getMessage());
		}
	}
}