package sportszer.dynamodb.table.scripts;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DeleteEventTable {

	public static void main(String[] args) {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient().withEndpoint("http://localhost:8000");
		DynamoDB dynamoDB = new DynamoDB(client);

		String tableName = "Event";

		try {
			System.out.println("Attempting to delete table; please wait...");

			Table table = dynamoDB.getTable(tableName);
			table.delete();

			table.waitForDelete();
			System.out.println("Success.");

		} catch (Exception e) {
			System.err.println("Unable to delete table.");
			System.err.println(e.getMessage());
		}
	}
}
