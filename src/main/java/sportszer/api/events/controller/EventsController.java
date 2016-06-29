package sportszer.api.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import sportszer.api.events.bean.Event;
import sportszer.api.events.dao.EventsRetriever;

/**
 * Defines Sportszer Events API
 * 
 * @author Siddharth Pandey
 * @since May 20, 2016
 */
@RestController
@Api(value = "Events API")
@RequestMapping("/v1")
public class EventsController {

	@Autowired
	private EventsRetriever eventsRetriever;

	@ApiOperation(value = "Retrieve Events")
	@RequestMapping(method = RequestMethod.GET, path = "/events", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Event.class) })
	public Event[] retrieveEvents() {
		return eventsRetriever.retrieveEvents();
	}

}
