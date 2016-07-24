package sportszer.api.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import sportszer.api.events.bean.Event;
import sportszer.api.events.dao.EventsDAO;

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
	private EventsDAO eventsDAO;

	@ApiOperation(value = "Retrieve Events")
	@RequestMapping(method = RequestMethod.GET, path = "/events", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Event.class) })
	public HttpEntity<Event[]> retrieveEvents() {
		return new ResponseEntity<Event[]>(eventsDAO.retrieveEvents(), HttpStatus.OK);
	}

	@ApiOperation(value = "Add Event")
	@RequestMapping(method = RequestMethod.POST, path = "/events", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public HttpStatus addEvent(@RequestBody Event event) {
		eventsDAO.addEvent(event);
		return HttpStatus.OK;
	}

	@ApiOperation(value = "Update Event")
	@RequestMapping(method = RequestMethod.PUT, path = "/events", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public HttpStatus updateEvent(@RequestBody Event event) {
		eventsDAO.updateEvent(event);
		return HttpStatus.OK;
	}

	@ApiOperation(value = "Delete Event")
	@RequestMapping(method = RequestMethod.DELETE, path = "/events", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public HttpStatus deleteEvent(@RequestBody Event event) {
		eventsDAO.deleteEvent(event);
		return HttpStatus.OK;
	}
}
