package mobi.ingogo.interview.controller;

import mobi.ingogo.interview.dao.IGeopositionDAO;
import mobi.ingogo.interview.dto.GeoPositionDto;
import mobi.ingogo.interview.dto.RouteRequestDto;
import mobi.ingogo.interview.dto.RouteResponseDto;
import mobi.ingogo.interview.model.Position;
import mobi.ingogo.interview.service.directions.DirectionsResponse;
import mobi.ingogo.interview.service.directions.DirectionsService;
import mobi.ingogo.interview.service.geocoder.GeocodeResult;
import mobi.ingogo.interview.service.geocoder.GeocoderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/api/geo"})
public class GeoApiController {

	@Autowired
	private DirectionsService directionsService;

	@Autowired
	private GeocoderService geocoderService;

	@Autowired
	private IGeopositionDAO geopositionDAO;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/route", method = RequestMethod.POST)
	public ResponseEntity<RouteResponseDto> route(@RequestBody RouteRequestDto request) {
		geopositionDAO.addRouteRequest(request);

		Double pickupLatitude = Double.parseDouble(request.getPickup().getLatitude());
		Double pickupLongitude = Double.parseDouble(request.getPickup().getLongitude());
		Double destinationLatitude = Double.parseDouble(request.getDropoff().getLatitude());
		Double destinationLongitude = Double.parseDouble(request.getDropoff().getLongitude());

		Position origin = new Position(pickupLatitude,pickupLongitude);
		Position destination = new Position(destinationLatitude,destinationLongitude);
		DirectionsResponse directionsResponse = directionsService.getDirections(origin, destination);

		RouteResponseDto response = new RouteResponseDto();
		response.setPickup(request.getPickup());
		response.setDropoff(request.getDropoff());
		logger.info("Encoded Polyline: " + directionsResponse.getEncodedPolyline().getEncodedPath());
		response.setEncodedPolyline(directionsResponse.getEncodedPolyline().getEncodedPath());
		logger.info("Distance: " + directionsResponse.getDistance().humanReadable);
		response.setDistance(directionsResponse.getDistance().humanReadable);
		logger.info("Duration: " + directionsResponse.getDuration().humanReadable);
		response.setDuration(directionsResponse.getDuration().humanReadable);
		geopositionDAO.addRouteResponse(response);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/positionEcho", method = RequestMethod.POST)
	public ResponseEntity<GeoPositionDto> position(@RequestBody GeoPositionDto position) {

		logger.debug("Received position: {}, {}", position.getLatitude(), position.getLongitude());

		return new ResponseEntity<>(position, HttpStatus.OK);
	}

	@RequestMapping(value = "/locationInfo", method = RequestMethod.POST)
	public ResponseEntity<GeocodeResult> locationInfo(@RequestBody GeoPositionDto geoPositionDto) {

		Double pickupLatitude = Double.parseDouble(geoPositionDto.getLatitude());
		Double pickupLongitude = Double.parseDouble(geoPositionDto.getLongitude());

		Position position = new Position(pickupLatitude,pickupLongitude);
		GeocodeResult result = geocoderService.reverseGeocode(position);
		logger.info("Suburb: " + result.getSuburb());
		logger.info("Street Address: " + result.getStreetAddress());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
