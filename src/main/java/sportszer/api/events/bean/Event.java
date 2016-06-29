package sportszer.api.events.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A Sportszer Event
 * 
 * @author Siddharth Pandey
 * @since May 20, 2016
 */
@ApiModel( value = "Event", description = "A Sportszer Event" )
public class Event { 

	@ApiModelProperty( value = "Event name", required = true )
	private String name;
	
	@ApiModelProperty( value = "Event description", required = true )
	private String description;

	public Event(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", description=" + description + "]";
	}	
}
