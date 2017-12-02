package mobi.ingogo.interview.service.directions;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import mobi.ingogo.interview.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DirectionsService {
	private GeoApiContext context = new GeoApiContext();

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public DirectionsService(@Value("${google.maps.key}") String apiKey) {
		context.setApiKey(apiKey);
	}

	public DirectionsResponse getDirections(Position origin, Position destination) {
		logger.debug("origin: {}, destination: {}", origin, destination);

		try {
			DirectionsApiRequest request = DirectionsApi.newRequest(context)
			.origin(new LatLng(origin.getLatitude(), origin.getLongitude()))
			.destination(new LatLng(destination.getLatitude(), destination.getLongitude()));

			DirectionsResult googleResponse = request.await();
			DirectionsResponse directionsResponse = new DirectionsResponse();
			directionsResponse.setEncodedPolyline(googleResponse.routes[0].overviewPolyline);
			directionsResponse.setDistance(googleResponse.routes[0].legs[0].distance);
			directionsResponse.setDuration(googleResponse.routes[0].legs[0].duration);

			return directionsResponse;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
