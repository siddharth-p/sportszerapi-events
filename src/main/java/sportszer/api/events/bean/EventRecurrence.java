package sportszer.api.events.bean;

import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

/**
 * Sportszer Event Recurrence
 * 
 * @author Siddharth Pandey
 * @since July 17, 2016
 */
@DynamoDBDocument
public class EventRecurrence {

	@DynamoDBAttribute
	private String frequency;
	
	@DynamoDBAttribute
	private Set<String> days;

	public EventRecurrence() {		
	}
	
	public EventRecurrence(String frequency, Set<String> days) {
		this.frequency = frequency;
		this.days = days;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Set<String> getDays() {
		return days;
	}

	public void setDays(Set<String> days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "EventRecurrence [frequency=" + frequency + ", days=" + days + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventRecurrence other = (EventRecurrence) obj;
		if (days == null) {
			if (other.days != null)
				return false;
		} else if (!days.equals(other.days))
			return false;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		return true;
	}	
}
